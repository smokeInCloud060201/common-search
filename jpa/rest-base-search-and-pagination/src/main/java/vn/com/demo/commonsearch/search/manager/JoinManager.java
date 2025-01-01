package vn.com.demo.commonsearch.search.manager;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

import java.util.HashMap;
import java.util.Map;

public class JoinManager {
    private final Map<String, Join<?, ?>> joinMap;

    public JoinManager() {
        joinMap = new HashMap<>();
    }


    public Join<?, ?> getOrCreateJoin(Root<?> root, String[] fields, JoinType joinType) {
        return getOrCreateJoin(root, fields, joinType, false);
    }

    public Join<?, ?> getOrCreateJoin(Root<?> root, String[] fields, JoinType joinType, boolean isJoinFetch) {
        if (joinType == null) {
            //default join will be left
            joinType = JoinType.LEFT;
        }
        Join<?, ?> join = null;
        int fieldLength = fields.length;
        for (int i = 0; i < fieldLength - 1; i++) {
            if (joinMap.containsKey(fields[i])) {
                join = joinMap.get(fields[i]);
            } else {
                if (join == null) {
                    join = isJoinFetch ? ((Join<?, ?>) root.fetch(fields[i], joinType)) : root.join(fields[i], joinType);
                } else {
                    join = isJoinFetch ? ((Join<?, ?>) join.fetch(fields[i], joinType)) : join.join(fields[i], joinType);
                }
                joinMap.put(fields[i], join);
            }
        }
        return join;
    }
}
