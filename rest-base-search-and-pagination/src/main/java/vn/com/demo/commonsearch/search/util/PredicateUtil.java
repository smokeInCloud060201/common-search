package vn.com.demo.commonsearch.search.util;

import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import vn.com.demo.commonsearch.search.dto.SearchRequest;
import vn.com.demo.commonsearch.search.manager.JoinManager;
import vn.com.demo.commonsearch.search.manager.proxy.FieldEntityManagerProxy;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static vn.com.demo.commonsearch.search.dto.Operator.Comparison.IN;
import static vn.com.demo.commonsearch.search.dto.Operator.Comparison.NOT_LIKE;

@Component
@RequiredArgsConstructor
@Slf4j
public class PredicateUtil {

    private final FieldEntityManagerProxy fieldEntityManagerProxy;

    public Predicate toPredicate(
            Root<?> root,
            CriteriaBuilder builder,
            JoinManager joinManager,
            SearchRequest.Filter filterCriteria) {

        if (filterCriteria == null) {
            return builder.conjunction();
        }
        String[] fields = splitField(filterCriteria.getField());
        Expression<?> expression = resolveExpression(root, builder, fields, joinManager);
        return createPredicate(root, builder, expression, filterCriteria);
    }

    public void fetchJoinWithSort(Root<?> root,
                             JoinManager joinManager,
                             SearchRequest.Sort sortCriteria) {
        String[] fields = splitField(sortCriteria.getField());

        if (fields.length > 1) {
            joinManager.getOrCreateJoin(root, fields, JoinType.LEFT, true);
        }
    }

    private String[] splitField(String field) {
        return StringUtils.split(field, ".");
    }

    private Expression<?> resolveExpression(Root<?> root, CriteriaBuilder builder ,String[] fields, JoinManager joinManager) {
        if (fields == null || fields.length == 0) {
            return builder.conjunction();
        }
        Join<?, ?> join = joinManager.getOrCreateJoin(root, fields, JoinType.LEFT);
        if (join == null) {
            return root.get(fields[fields.length - 1]);
        } else {
            return join.get(fields[fields.length - 1]);
        }
    }

    private Predicate createPredicate(Root<?> root, CriteriaBuilder builder, Expression<?> expression, SearchRequest.Filter filterCriteria) {
        try {
            Object value = parseValue(root, filterCriteria);
            return applyOperator(builder, expression, value, filterCriteria);
        } catch (Exception e) {
            log.error("Error processing filter: {} with value: {}", filterCriteria.getField(), filterCriteria.getValue(), e);
            return builder.disjunction();
        }
    }

    private Object parseValue(Root<?> root, SearchRequest.Filter filterCriteria) {
        Class<?> typeClass = fieldEntityManagerProxy.getFieldTypeByName(root.getJavaType().getSimpleName(), filterCriteria.getField());
        if (typeClass == null) {
            return null;
        }
        if (filterCriteria.getOperator() == IN) {
            return  ((Collection<?>) filterCriteria.getValue()).stream().map(s -> StringParserUtil.parseStringToObjectByType((String) s, typeClass)).
                    collect(Collectors.toSet());
        }
        String valueStr = String.valueOf(filterCriteria.getValue());
        return StringParserUtil.parseStringToObjectByType(valueStr, typeClass);
    }

    @SuppressWarnings("unchecked")
    private <Y extends Comparable<? super Y>> Predicate applyOperator(CriteriaBuilder builder, Expression<?> expression, Object value, SearchRequest.Filter filterCriteria) {
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

    private Predicate buildLikePredicate(CriteriaBuilder builder, Expression<?> expression, Object value, boolean negate) {
        Expression<String> stringExpression = builder.upper(expression.as(String.class));
        Predicate likePredicate = builder.like(stringExpression, "%" + value + "%");
        return negate ? builder.not(likePredicate) : likePredicate;
    }
}
