package org.example.graphql.services;

import com.querydsl.core.types.EntityPath;
import org.example.graphql.entity.QStudent;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.graphql.data.query.QuerydslDataFetcher;

public class QueryBuilderCustom implements QuerydslBinderCustomizer<EntityPath<?>> {


    @Override
    public void customize(QuerydslBindings bindings, EntityPath<?> root) {
    }
}
