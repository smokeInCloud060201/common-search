package vn.com.demo.commonsearch.search.dto;


import lombok.Getter;
import vn.com.demo.commonsearch.anno.IncludeSearchKey;

import java.lang.reflect.Field;
import java.util.*;


@Getter
public class EntityField {

    public EntityField(Field field) {
        field.setAccessible(true);
        fieldType = field.getType();
        fieldName = field.getName();
        if (field.isAnnotationPresent(IncludeSearchKey.class)) {
            IncludeSearchKey includeSearchKey = field.getAnnotation(IncludeSearchKey.class);
            List<String> searchKeys = Arrays.asList(includeSearchKey.value());

            this.searchKeys = searchKeys.isEmpty()
                    ? List.of(fieldName)
                    : searchKeys.stream()
                    .map(s -> fieldName + "." + s)
                    .toList();
            this.searchable = true;
        } else {
            this.searchable = false;
            this.searchKeys = List.of(fieldName);
        }
    }
    private final boolean searchable;

    private final List<String> searchKeys;

    private final Class<?> fieldType;

    private final String fieldName;
}