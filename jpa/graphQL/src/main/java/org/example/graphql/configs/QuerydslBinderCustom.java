package org.example.graphql.configs;

import com.querydsl.core.types.EntityPath;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;

@Component
public class QuerydslBinderCustom<T extends EntityPath<?>> implements QuerydslBinderCustomizer<T> {

    @Override
    public void customize(QuerydslBindings bindings, T root) {
        root.
    }
}
