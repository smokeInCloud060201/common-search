package vn.com.demo.commonsearch.search.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class SearchRequest {

    @Builder.Default
    private int page = 0;

    @Builder.Default
    private int size = 10;

    @JsonProperty("searchAllKey")
    private String searchAllKey;

    private Sort sort;


    private List<Filter> filterList;

    @Setter
    @Getter
    @Builder
    public static class Sort {
        private String field;
        private boolean asc;
    }

    @Setter
    @Getter
    @Builder
    public static class Filter {
        private String field;
        private Object value;
        private Operator.Comparison operator;
    }
}
