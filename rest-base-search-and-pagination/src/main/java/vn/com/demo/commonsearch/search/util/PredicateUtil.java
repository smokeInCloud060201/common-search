package vn.com.demo.commonsearch.search.util;

import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import vn.com.demo.commonsearch.base.BaseEntity;
import vn.com.demo.commonsearch.search.dto.SearchRequest;
import vn.com.demo.commonsearch.search.manager.JoinManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static vn.com.demo.commonsearch.constants.DateTimeConstants.LOCAL_DATE_TIME_FORMAT;
import static vn.com.demo.commonsearch.search.dto.Operator.Comparison.IN;
import static vn.com.demo.commonsearch.search.dto.Operator.Comparison.NOT_LIKE;
import static vn.com.demo.commonsearch.search.dto.Type.TEXT;

@Slf4j
public class PredicateUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT);

    public static Predicate toPredicate(
            Root<?> root,
            CriteriaQuery<?> criteriaQuery,
            CriteriaBuilder builder,
            JoinManager joinManager,
            SearchRequest.Filter filterCriteria) {

        if (filterCriteria == null) {
            return builder.conjunction();
        }

        String[] fields = splitField(filterCriteria.getField());
        Expression<?> expression = resolveExpression(root, builder, fields);
        return createPredicate(root, builder, expression, filterCriteria);
    }

    private static String[] splitField(String field) {
        return StringUtils.split(field, ".");
    }

    private static Expression<?> resolveExpression(Root<?> root, CriteriaBuilder builder, String[] fields) {
        Join<?, BaseEntity> join = null;

        for (int i = 0; i < fields.length - 1; i++) {
            join = (join == null) ? root.join(fields[i], JoinType.INNER) : join.join(fields[i], JoinType.INNER);
        }

        return (join != null) ? join.get(fields[fields.length - 1]) : root.get(fields[fields.length - 1]);
    }

    private static Predicate createPredicate(Root<?> root, CriteriaBuilder builder, Expression<?> expression, SearchRequest.Filter filterCriteria) {
        try {
            Object value = parseValue(filterCriteria);
            return applyOperator(builder, expression, value, filterCriteria);
        } catch (Exception e) {
            log.error("Error processing filter: {} with value: {}", filterCriteria.getField(), filterCriteria.getValue(), e);
            return builder.disjunction();
        }
    }

    private static Object parseValue(SearchRequest.Filter filterCriteria) {
        if (filterCriteria.getOperator() == IN) {
            return new HashSet<>((Collection<?>) filterCriteria.getValue());
        }

        String valueStr = String.valueOf(filterCriteria.getValue());
        return switch (filterCriteria.getType()) {
            case NUMERIC -> Long.parseLong(valueStr);
            case DATE_TIME -> LocalDateTime.parse(valueStr, DATE_TIME_FORMATTER);
            case BOOLEAN -> Boolean.parseBoolean(valueStr);
            case ENUM -> Enum.valueOf((Class<Enum>) filterCriteria.getValue().getClass(), valueStr);
            default -> (filterCriteria.getType() == TEXT) ? valueStr.toUpperCase() : valueStr;
        };
    }

    @SuppressWarnings("unchecked")
    private static <Y extends Comparable<? super Y>> Predicate applyOperator(CriteriaBuilder builder, Expression<?> expression, Object value, SearchRequest.Filter filterCriteria) {
        Expression<Y> typedExpression = (Expression<Y>) expression;

        return switch (filterCriteria.getOperator()) {
            case GREATER -> builder.greaterThan(typedExpression, (Y) value);
            case LESS -> builder.lessThan(typedExpression, (Y) value);
            case GREATER_EQUAL -> builder.greaterThanOrEqualTo(typedExpression, (Y) value);
            case LESS_EQUAL -> builder.lessThanOrEqualTo(typedExpression, (Y) value);
            case IN -> typedExpression.in((Set<Y>) value);
            case LIKE, NOT_LIKE -> buildLikePredicate(builder, typedExpression, value, NOT_LIKE.equals(filterCriteria.getOperator()));
            case NOT_EQUAL -> builder.notEqual(typedExpression, value);
            default -> builder.equal(typedExpression, value);
        };
    }

    private static Predicate buildLikePredicate(CriteriaBuilder builder, Expression<?> expression, Object value, boolean negate) {
        Expression<String> stringExpression = builder.upper(expression.as(String.class));
        Predicate likePredicate = builder.like(stringExpression, "%" + value.toString().toUpperCase() + "%");
        return negate ? builder.not(likePredicate) : likePredicate;
    }
}
