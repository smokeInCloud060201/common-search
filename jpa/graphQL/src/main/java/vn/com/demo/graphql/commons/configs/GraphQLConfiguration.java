package vn.com.demo.graphql.commons.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.ClassNameTypeResolver;
import vn.com.demo.graphql.commons.annotations.GraphQLTypeMapping;
import vn.com.demo.graphql.commons.util.ReflectionUtil;

import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class GraphQLConfiguration {
    private final GraphQLExceptionHandler graphQLExceptionHandler;

    @Bean
    public GraphQlSourceBuilderCustomizer sourceBuilderCustomizer() {

        List<Pair<GraphQLTypeMapping, Class<?>>> pairList = ReflectionUtil.getPairsClassByAnnotation(GraphQLTypeMapping.class);
        ClassNameTypeResolver classNameTypeResolver = new ClassNameTypeResolver();

        pairList.forEach(pair -> classNameTypeResolver.addMapping(pair.getRight(), pair.getLeft().value()));

        return builder ->
                builder
                        .defaultTypeResolver(classNameTypeResolver)
                        .configureGraphQl(config -> config.defaultDataFetcherExceptionHandler(graphQLExceptionHandler));
    }
}
