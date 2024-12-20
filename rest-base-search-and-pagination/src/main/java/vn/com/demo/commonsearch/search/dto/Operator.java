package vn.com.demo.commonsearch.search.dto;

public final class Operator {
    public enum Comparison {
        EQUAL,
        GREATER,
        LESS,
        GRATER_EQUAL,
        LESS_EQUAL,
        NOT_EQUAL
    }

    public enum Logical {
        AND,
        OR
    }
}
