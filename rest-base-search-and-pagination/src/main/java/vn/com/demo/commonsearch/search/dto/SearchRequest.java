package vn.com.demo.commonsearch.search.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@Setter
public class SearchRequest {
    private int page;

    private int size;

    @JsonProperty("searchAllKey")
    private String searchAllKey;


    private List<Sort> sortList;


    private List<Filter> filterList;

    @Setter
    @Getter
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
        private Type type;
    }
}
