package vn.com.demo.commonsearch.search.dto;

public final class Operator {
    public enum Comparison {
        EQUAL,
        GREATER,
        LESS,
        GREATER_EQUAL,
        LESS_EQUAL,
        NOT_EQUAL,
        IN,
        LIKE,
        NOT_LIKE
    }

    public enum Logical {
        AND,
        OR
    }
}
