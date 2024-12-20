package org.example.graphql.configs;

import graphql.execution.ExecutionId;
import graphql.execution.ExecutionIdProvider;

import java.util.UUID;

public class CustomExecutionIdProvider implements ExecutionIdProvider {
    @Override
    public ExecutionId provide(String query, String operationName, Object context) {
        return ExecutionId.generate();
    }

}
