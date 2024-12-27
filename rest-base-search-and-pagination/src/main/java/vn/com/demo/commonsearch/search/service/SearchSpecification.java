package vn.com.demo.commonsearch.search.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vn.com.demo.commonsearch.base.BaseEntity;
import vn.com.demo.commonsearch.search.manager.JoinManager;
import vn.com.demo.commonsearch.search.manager.proxy.FieldEntityManagerProxy;

@AllArgsConstructor
@Component
public class SearchSpecification {

    private final FieldEntityManagerProxy fieldEntityManagerProxy;

//    private JoinManager joinManager;
//
//
//    public Specification<T> getSearchSpecification(String searchKey) {
//        if (StringUtils.isEmpty(searchKey)) {
//            return null;
//        }
//
//        searchKey = searchKey.toLowerCase();
//        return (root, query, criteriaBuilder) -> {
//            return null;
//        };
//    }
}
