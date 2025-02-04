package vn.com.demo.graphql.commons.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import vn.com.demo.graphql.commons.annotations.GraphQLTypeMapping;

import java.time.ZonedDateTime;

@SuperBuilder
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@GraphQLTypeMapping("SearchResultResponse")
public class BaseResponse <T> {

    private T data;

    private MetaData metaData;

    private String errorCode;

    private String errorMessage;

    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime timestamp = ZonedDateTime.now();

    @Builder
    @Getter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @GraphQLTypeMapping("MetaData")
    public static class MetaData {

        @Builder.Default
        private int page = 0;

        @Builder.Default
        private int size = 10;

    }

}
