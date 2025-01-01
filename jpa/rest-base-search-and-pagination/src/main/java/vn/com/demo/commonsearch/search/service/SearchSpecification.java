package vn.com.demo.commonsearch.search.service;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import vn.com.demo.commonsearch.base.BaseEntity;
import vn.com.demo.commonsearch.search.dto.EntityField;
import vn.com.demo.commonsearch.search.dto.Operator;
import vn.com.demo.commonsearch.search.dto.SearchRequest;
import vn.com.demo.commonsearch.search.manager.JoinManager;
import vn.com.demo.commonsearch.search.manager.proxy.FieldEntityManagerProxy;
import vn.com.demo.commonsearch.search.util.PredicateUtil;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class SearchSpecification {

    private final FieldEntityManagerProxy fieldEntityManagerProxy;
    private final PredicateUtil predicateUtil;
    private final RestClient.Builder builder;

    public <T> Specification<T> getAllSpecifications(SearchRequest searchRequest, Class<? extends BaseEntity> rootClazz) {
        JoinManager joinManager = new JoinManager();
        Specification<T> specification =  Specification.where(null);
        if (searchRequest != null) {
            if (searchRequest.getSort() != null) {
                //Join fetch for order with distinct - Only needed with PostgreSQL, for MSSQL or MySQL,... Dont really need this one
                specification = specification.and((root, query, criteriaBuilder) -> {
                    predicateUtil.fetchJoinWithSort(root, joinManager, searchRequest.getSort());
                    return null;
                });
            }
            if (searchRequest.getSearchAllKey() != null && !searchRequest.getSearchAllKey().isEmpty()) {
                specification = specification.and(getSearchSpecification(searchRequest.getSearchAllKey(), joinManager, rootClazz));
            }
            if (searchRequest.getFilterList() != null && !searchRequest.getFilterList().isEmpty()) {
                specification = specification.and(getFilterSpecification(searchRequest.getFilterList(), joinManager));
            }
        }
        return specification;
    }

    public Pageable getPageable(SearchRequest searchRequest) {
        Pageable pageable = null;
        if (searchRequest != null) {
            Sort sort = Sort.by(searchRequest.getSort().getField());
            boolean isAsc = searchRequest.getSort().isAsc();
            pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getSize(), isAsc ? sort.ascending() : sort.descending());
        }
        return pageable;
    }

    private <T> Specification<T> getFilterSpecification(List<SearchRequest.Filter> filterList, JoinManager joinManager) {
        return (root, query, criteriaBuilder) -> {
            if (query != null && !query.isDistinct()) {
                query.distinct(true);
            }
            List<Predicate> predicates = new ArrayList<>();
            for (SearchRequest.Filter filter : filterList) {
                predicates.add(predicateUtil.toPredicate(root, criteriaBuilder, joinManager, filter));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


    private <T> Specification<T> getSearchSpecification(final String searchKey, JoinManager joinManager,  Class<? extends BaseEntity> rootClazz) {
        List<EntityField> fields =  fieldEntityManagerProxy.getEntityFieldOfEntity(rootClazz.getSimpleName()).stream()
                .filter(EntityField::isSearchable).toList();

        return (root, query, criteriaBuilder) -> {
            if (query != null && !query.isDistinct()) {
                query.distinct(true);
            }
            List<Predicate> predicates = new ArrayList<>();
                List<SearchRequest.Filter> filterList = fields.stream().flatMap(s -> s.getSearchKeys().stream().map(key ->
                        SearchRequest.Filter.builder()
                                .value(searchKey)
                                .operator(Operator.Comparison.LIKE)
                                .field(key)
                                .build()))
                        .toList();

                for (SearchRequest.Filter filter : filterList) {
                    predicates.add(predicateUtil.toPredicate(root, criteriaBuilder, joinManager, filter));
                }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
