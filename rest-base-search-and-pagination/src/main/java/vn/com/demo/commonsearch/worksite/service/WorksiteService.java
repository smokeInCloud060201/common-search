package vn.com.demo.commonsearch.worksite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.com.demo.commonsearch.search.dto.Operator;
import vn.com.demo.commonsearch.search.dto.SearchRequest;
import vn.com.demo.commonsearch.search.dto.Type;
import vn.com.demo.commonsearch.search.service.SearchSpecification;
import vn.com.demo.commonsearch.worksite.entities.Workday;
import vn.com.demo.commonsearch.worksite.entities.Worksite;
import vn.com.demo.commonsearch.worksite.repository.WorkdayRepository;
import vn.com.demo.commonsearch.worksite.repository.WorksiteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorksiteService implements CommandLineRunner {

    private final WorksiteRepository worksiteRepository;
    private final SearchSpecification searchSpecification;
    private final WorkdayRepository workdayRepository;


    public Page<Worksite> searchWorksite(SearchRequest searchRequest) {

        Specification<Workday> workdaySpecification = searchSpecification.getAllSpecifications(searchRequest, Workday.class);

        Pageable pageable = PageRequest.of(0, 100);
        Page<Workday> workdays = workdayRepository.findAll(workdaySpecification, pageable);

        return null;
    }

    @Override
    public void run(String... args) throws Exception {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setSearchAllKey("worksite dummy");

        List<SearchRequest.Filter> filterList = new ArrayList<>();
        filterList.add(SearchRequest.Filter.builder()
                        .field("tbm")
                        .value("tbm dummy")
                        .operator(Operator.Comparison.EQUAL)
                        .type(Type.TEXT)
                .build());


        searchRequest.setFilterList(
                filterList
        );
        searchWorksite(searchRequest);
    }
}
