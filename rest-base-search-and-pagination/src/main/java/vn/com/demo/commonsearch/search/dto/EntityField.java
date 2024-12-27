package vn.com.demo.commonsearch.search.dto;


import lombok.Getter;
import vn.com.demo.commonsearch.anno.IncludeSearchKey;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;


public class EntityField {

    public EntityField(Field field) {
        this.field = field;
        if (field.isAnnotationPresent(IncludeSearchKey.class)) {
            this.searchable = true;

            IncludeSearchKey includeSearchKey = field.getAnnotation(IncludeSearchKey.class);
            this.searchList = List.of(includeSearchKey.value());
        } else {
            this.searchable = false;
            this.searchList = Collections.emptyList();
        }
    }

    @Getter
    private final Field field;

    @Getter
    private final boolean searchable;

    @Getter
    private final List<String> searchList;
}
