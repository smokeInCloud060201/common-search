package vn.com.demo.commonsearch.search.manager;

import jakarta.persistence.criteria.Join;

import java.util.HashMap;
import java.util.Map;

public class JoinManager {
    private Map<String, Join<?, ?>> joinMap;

    public JoinManager() {
        joinMap = new HashMap<>();
    }

    public Join<?, ?> getOrCreateJoin(String joinKey) {
        Join<?, ?> join = joinMap.get(joinKey);
        if (join == null) {

        }

        return join;
    }


}
