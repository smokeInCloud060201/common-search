package vn.com.demo.commonsearch.search.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import vn.com.demo.commonsearch.anno.IncludeSearchKey;
import vn.com.demo.commonsearch.search.dto.SearchRequest;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@AllArgsConstructor
public class SearchRequestMapper {

    public Pageable getPageable(SearchRequest searchRequest) {
        List<Sort.Order> sortList = searchRequest.getSortList().stream()
                .map(s -> Sort.Order.by(s.getField()).with(s.isAsc() ? Sort.Direction.ASC : Sort.Direction.DESC))
                .toList();

        Sort sort = Sort.by(sortList);

        return PageRequest.of(searchRequest.getPage(), searchRequest.getSize(), sort);
    }

    public <T> Specification<T> getSpecifications(SearchRequest searchRequest) {
        String searchKey = searchRequest.getSearchAllKey();
        if (!StringUtils.isEmpty(searchKey)) {

        }
//        return ((root, query, criteriaBuilder) ->  {
//
//        })

        return null;
    }

    private <T> Specification<T> getSpecificationByKeySearch(String keySearch, Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<Field> fieldIncludeSearchKeys = new ArrayList<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(IncludeSearchKey.class)) {
                fieldIncludeSearchKeys.add(field);
            }
        }

        if (!fieldIncludeSearchKeys.isEmpty()) {

            for (Field field : fields) {
//                String searchKey = searchKeyMaps.get(field);
//                if (StringUtils.isEmpty(searchKey)) {
//                    continue;
//                }
//                String[] searchKeyArr = searchKey.split("\\.");
//                if (searchKeyArr.length > 0) {}

            }
        }


        return null;
    }
}
