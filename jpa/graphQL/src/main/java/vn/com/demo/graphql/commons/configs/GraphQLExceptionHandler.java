package vn.com.demo.graphql.commons.configs;

import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class GraphQLExceptionHandler implements DataFetcherExceptionHandler {
    @Override
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters handlerParameters) {

        log.debug("handleException {} with envs {}", handlerParameters.getException().getMessage(), handlerParameters.getDataFetchingEnvironment());
        return CompletableFuture.completedFuture(DataFetcherExceptionHandlerResult.newResult(GraphQLError.newError()
                                .message(handlerParameters.getException().getMessage())
                        .build())
                .build());


    }
}
