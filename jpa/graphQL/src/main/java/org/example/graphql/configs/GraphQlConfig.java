package org.example.graphql.configs;

import graphql.schema.GraphQLScalarType;
import graphql.schema.idl.SchemaDirectiveWiring;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration(proxyBeanMethods = false)
public class GraphQlConfig {
    @Bean
    public GraphQlSourceBuilderCustomizer sourceBuilderCustomizer() {
        return (builder) ->
                builder.configureGraphQl((graphQlBuilder) ->
                        graphQlBuilder.executionIdProvider(new CustomExecutionIdProvider()));
    }

//    @Bean
//    public RuntimeWiringConfigurer runtimeWiringConfigurer(BookRepository repository) {
//        GraphQLScalarType scalarType = ... ;
//        SchemaDirectiveWiring directiveWiring = ... ;
//        return wiringBuilder -> wiringBuilder
//                .scalar(scalarType)
//                .directiveWiring(directiveWiring);
//    }
}
