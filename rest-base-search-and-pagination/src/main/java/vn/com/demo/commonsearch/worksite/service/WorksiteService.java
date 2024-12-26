package vn.com.demo.commonsearch.worksite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vn.com.demo.commonsearch.search.service.SearchSpecification;
import vn.com.demo.commonsearch.worksite.entities.Worksite;
import vn.com.demo.commonsearch.worksite.repository.WorksiteRepository;

@Service
@RequiredArgsConstructor
public class WorksiteService implements CommandLineRunner {

    private final WorksiteRepository worksiteRepository;


    public Page<Worksite> searchWorksite() {


        return null;
    }

    @Override
    public void run(String... args) throws Exception {
        SearchSpecification<Worksite> searchSpec = new SearchSpecification<>();
        searchSpec.getSpecification(null);
    }
}
