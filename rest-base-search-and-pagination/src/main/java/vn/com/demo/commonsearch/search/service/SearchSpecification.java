package vn.com.demo.commonsearch.search.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import vn.com.demo.commonsearch.base.BaseEntity;
import vn.com.demo.commonsearch.search.dto.SearchRequest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SearchSpecification<T extends BaseEntity> {

    private Class<T> entityClass;

    public Specification<T> getSpecification(SearchRequest searchRequest) {
        return ((root, query, criteriaBuilder) -> {
            Class<?> rootType = root.getClass();
            System.out.println(rootType);
            return null;

        });
    }
}
