package vn.com.demo.commonsearch.search.dto;


import lombok.Getter;
import vn.com.demo.commonsearch.anno.IncludeSearchKey;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;


@Getter
public class EntityField {

    public EntityField(Field field) {
        field.setAccessible(true);
        this.field = field;
        fieldType = field.getType();
        fieldName = field.getName();
        if (field.isAnnotationPresent(IncludeSearchKey.class)) {
            IncludeSearchKey includeSearchKey = field.getAnnotation(IncludeSearchKey.class);
            this.searchList = List.of(includeSearchKey.value());
            this.searchable = !searchList.isEmpty();
        } else {
            this.searchable = false;
            this.searchList = Collections.emptyList();
        }
    }

    private final Field field;

    private final boolean searchable;

    private final List<String> searchList;

    private final Class<?> fieldType;

    private final String fieldName;
}
