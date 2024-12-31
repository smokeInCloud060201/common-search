package vn.com.demo.commonsearch.search.service;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vn.com.demo.commonsearch.base.BaseEntity;
import vn.com.demo.commonsearch.search.dto.EntityField;
import vn.com.demo.commonsearch.search.dto.Operator;
import vn.com.demo.commonsearch.search.dto.SearchRequest;
import vn.com.demo.commonsearch.search.dto.Type;
import vn.com.demo.commonsearch.search.manager.JoinManager;
import vn.com.demo.commonsearch.search.manager.proxy.FieldEntityManagerProxy;
import vn.com.demo.commonsearch.search.util.PredicateUtil;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class SearchSpecification {

    private final FieldEntityManagerProxy fieldEntityManagerProxy;

    public <T> Specification<T> getAllSpecifications(SearchRequest searchRequest, Class<? extends BaseEntity> rootClazz) {
        final JoinManager joinManager = new JoinManager();
        Specification<T> specification = getSearchSpecification(searchRequest.getSearchAllKey(), joinManager, rootClazz);



        return specification;
    }



    private <T> Specification<T> getSearchSpecification(final String searchKey, JoinManager joinManager,  Class<? extends BaseEntity> rootClazz) {
        if (StringUtils.isEmpty(searchKey)) {
            return null;
        }

        List<EntityField> fields =  fieldEntityManagerProxy.getEntityFieldOfEntity(rootClazz.getSimpleName()).stream()
                .filter(EntityField::isSearchable).toList();

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (EntityField field : fields) {
                List<String> listSearchKey = field.getSearchList();
                List<SearchRequest.Filter> filterList = listSearchKey.stream().map(s -> SearchRequest.Filter.builder()
                        .type(Type.TEXT)
                        .value(searchKey)
                        .operator(Operator.Comparison.IN)
                        .field(s)
                        .build())
                        .toList();
                if (!listSearchKey.isEmpty()) {

                    for (SearchRequest.Filter filter : filterList) {
                        predicates.add(PredicateUtil.toPredicate(root, query, criteriaBuilder, joinManager, filter));
                    }
                } else {
                    predicates.add(criteriaBuilder.equal(
                            criteriaBuilder.lower(root.get(field.getFieldName()).as(String.class)), searchKey.toLowerCase())
                    );
                }
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
