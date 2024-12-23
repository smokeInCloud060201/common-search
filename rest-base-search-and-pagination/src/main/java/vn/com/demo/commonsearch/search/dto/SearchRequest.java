package vn.com.demo.commonsearch.search.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Getter
    public static class Sort {
        private String field;
        private boolean asc;
    }

    @Getter
    public static class Filter {
        private String field;
        private String value;
    }
}
