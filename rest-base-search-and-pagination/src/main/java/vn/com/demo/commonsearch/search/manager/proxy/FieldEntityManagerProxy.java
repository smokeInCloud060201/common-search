package vn.com.demo.commonsearch.search.manager.proxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.com.demo.commonsearch.base.BaseEntity;
import vn.com.demo.commonsearch.search.manager.FieldEntityManger;
import vn.com.demo.commonsearch.search.dto.EntityField;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class FieldEntityManagerProxy {

    private final FieldEntityManger fieldEntityManger;

    public Class<? extends BaseEntity> getClassOfEntityName(String entityName) {
        return fieldEntityManger.getFieldsOfEntity().keySet().stream()
                .filter(c -> c.getSimpleName().equalsIgnoreCase(entityName))
                .findFirst()
                .orElse(null);
    }
    public List<EntityField> getEntityFieldOfEntity(String entityName) {
        return fieldEntityManger.getFieldsOfEntity().entrySet().stream()
                .filter(c -> c.getKey().getSimpleName().equalsIgnoreCase(entityName))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null);
    }
}
