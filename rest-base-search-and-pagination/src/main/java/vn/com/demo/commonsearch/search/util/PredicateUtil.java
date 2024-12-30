package vn.com.demo.commonsearch.search.util;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import vn.com.demo.commonsearch.base.BaseEntity;
import vn.com.demo.commonsearch.search.dto.Operator;
import vn.com.demo.commonsearch.search.manager.JoinManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.hibernate.grammars.hql.HqlParser.GREATER_EQUAL;
import static vn.com.demo.commonsearch.constants.DateTimeConstants.LOCAL_DATE_TIME_FORMAT;
import static vn.com.demo.commonsearch.search.dto.Operator.Comparison.GREATER;
import static vn.com.demo.commonsearch.search.dto.Operator.Comparison.IN;
import static vn.com.demo.commonsearch.search.dto.Operator.Comparison.LESS;
import static vn.com.demo.commonsearch.search.dto.Operator.Comparison.LESS_EQUAL;
import static vn.com.demo.commonsearch.search.dto.Operator.Comparison.LIKE;
import static vn.com.demo.commonsearch.search.dto.Operator.Comparison.NOT_EQUAL;
import static vn.com.demo.commonsearch.search.dto.Operator.Comparison.NOT_LIKE;
import static vn.com.demo.commonsearch.search.dto.Type.BOOLEAN;
import static vn.com.demo.commonsearch.search.dto.Type.DATE_TIME;
import static vn.com.demo.commonsearch.search.dto.Type.ENUM;
import static vn.com.demo.commonsearch.search.dto.Type.NUMERIC;
import static vn.com.demo.commonsearch.search.dto.Type.TEXT;

@Slf4j
public class PredicateUtil {

    public Predicate toPredicate(
            Root<?> root,
            CriteriaQuery<?> criteriaQuery,
            CriteriaBuilder builder,
            JoinManager joinManager) {

        if (filterCriteria == null) return builder.equal(builder.literal(1), 1);

        // find nested field with pattern string.string
        int nestedLevel = StringUtils.countMatches(filterCriteria.getField(), ".");
        if (nestedLevel > 0) {
            String fullField = filterCriteria.getField();
            String[] fields = fullField.split("\\.");
            // TODO: Add field to filterCriteria to specify which type of join
            Join<E, BaseEntity> join = root.join(fields[0], JoinType.INNER);
            if (nestedLevel == 1) {
                return getPredicate(root, builder, join.get(fields[1]));
            }
            Join<BaseEntity, BaseEntity> joinNext = join.join(fields[1], JoinType.INNER);
            if (nestedLevel == 2) {
                return getPredicate(root, builder, joinNext.get(fields[2]));
            }
            for (int i = 2; i < nestedLevel; i++) {
                joinNext = joinNext.join(fields[i], JoinType.INNER);
            }
            return getPredicate(root, builder, joinNext.get(fields[fields.length - 1]));
        }
        return getPredicate(root, builder, handleField(root, builder));
    }

    /**
     * Return the predicate that combine the field, operator and value
     */
    private <Y extends Comparable<? super Y>> Predicate getPredicate(Root<E> root, CriteriaBuilder builder, Expression<? extends Y> expression) {
        if (filterCriteria.getOperator().equals(Operator.)) {
            Object trueValue = handleValue();
            return this.handleOperator(builder, (Expression<ComparableHashSet<Y>>) expression, new ComparableHashSet<>((HashSet<Y>) trueValue));
        }
        Expression<? extends Y> truePath = expression == null ? handleField(root, builder) : expression;
        try {
            Object trueValue = handleValue();
            return this.handleOperator(builder, truePath, (Y) trueValue);
        } catch (NumberFormatException ex) {
            log.error(ex.getMessage());
            return builder.disjunction();
        }
    }

    private <Y> Expression<Y> handleField(Root<E> root, CriteriaBuilder builder) {
        if (filterCriteria.getType() == TEXT && !filterCriteria.getOperator().equals(IN)) {
            return (Expression<Y>) builder.upper(root.get(filterCriteria.getField()));
        } else {
            return root.get(filterCriteria.getField());
        }
    }

    private Object handleValue() throws NumberFormatException {
        String filterCriteriaValueStr = filterCriteria.getValue().toString();
        if (filterCriteria.getOperator().equals(Operator.IN)) {
            return filterCriteria.getValue() == null ? null : new HashSet<>((Collection<?>) filterCriteria.getValue());
        }
        switch (filterCriteria.getType()) {
            case NUMERIC -> {
                return filterCriteria.getValue() == null ? null : Long.parseLong(filterCriteriaValueStr);
            }
            case DATE_TIME -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT);
                return LocalDateTime.parse(filterCriteriaValueStr, formatter);
            }
            case BOOLEAN -> {
                return filterCriteria.getValue() == null ? null : Boolean.valueOf(filterCriteriaValueStr);
            }
            case ENUM -> {
                return filterCriteria.getValue() == null ? null : Enum.valueOf((Class<Enum>) filterCriteria.getValue().getClass(), filterCriteriaValueStr);
            }
            default -> {
                int nestedLevel = StringUtils.countMatches(filterCriteria.getField(), ".");
                if (nestedLevel == 0) {
                    // Case not sensitive for normal field
                    return filterCriteria.getValue() == null ? null : filterCriteriaValueStr.toUpperCase();
                } else {
                    // Case sensitive for nested field
                    // TODO: Need to find a way to search case not sensitive for nested field
                    return filterCriteria.getValue() == null ? null : filterCriteriaValueStr;
                }
            }
        }
    }

    private <Y extends Comparable<? super Y>> Predicate handleOperator(CriteriaBuilder builder, Expression<? extends Y> expression, Y value) {
        switch (filterCriteria.getOperator()) {
            case GREATER:
                return builder.greaterThan(expression, value);
            case LESS:
                return builder.lessThan(expression, value);
            case GREATER_EQUAL: {
                return builder.greaterThanOrEqualTo(expression, value);
            }
            case LESS_EQUAL: {
                return builder.lessThanOrEqualTo(expression, value);
            }
            case IN: {
                return expression.in((Set) value);
            }
            case LIKE: {
                Expression<String> stringPath = builder.upper(expression.as(String.class));
                if (filterCriteria.getValue() != null) {
                    return builder.like(
                            stringPath, "%" + filterCriteria.getValue().toString().toUpperCase() + "%");
                } else {
                    return builder.isNull(expression);
                }
            }
            case NOT_LIKE: {
                Expression<String> stringPath = builder.upper(expression.as(String.class));
                if (filterCriteria.getValue() != null) {
                    return builder.notLike(
                            stringPath, "%" + filterCriteria.getValue().toString().toUpperCase() + "%");
                } else {
                    return builder.isNull(expression);
                }
            }
            case NOT_EQUAL: {
                if (filterCriteria.getValue() != null) {
                    return builder.notEqual(expression, value);
                } else {
                    return builder.isNotNull(expression);
                }
            }
            // EQUAL and default case
            default: {
                if (filterCriteria.getValue() != null) {
                    return builder.equal(expression, value);
                } else {
                    return builder.isNull(expression);
                }
            }
        }
    }

}
