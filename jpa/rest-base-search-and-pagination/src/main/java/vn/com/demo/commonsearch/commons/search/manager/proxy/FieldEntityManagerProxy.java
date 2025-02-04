package vn.com.demo.commonsearch.commons.search.manager.proxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.com.demo.commonsearch.commons.entity.BaseEntity;
import vn.com.demo.commonsearch.commons.search.manager.FieldEntityManger;
import vn.com.demo.commonsearch.commons.search.dto.EntityField;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Class<?> getFieldTypeByName(String entityName, String fieldName) {
        String[] fieldPaths = fieldName.split("\\.");
        return getFieldType(entityName, fieldPaths);
    }

    private Class<?> getFieldType(String entityName, String[] fieldPaths) {
        Map<String, EntityField> fieldMap = getEntityFieldMap(entityName);
        if (fieldMap.isEmpty()) {
            return null;
        }
        EntityField currentField = fieldMap.get(fieldPaths[0].toLowerCase());
        if (currentField == null) {
            return null;
        }
        if (fieldPaths.length == 1) {
            return currentField.getFieldType();
        }
        return getFieldType(currentField.getFieldType().getSimpleName(), Arrays.copyOfRange(fieldPaths, 1, fieldPaths.length));
    }

    private Map<String, EntityField> getEntityFieldMap(String entityName) {
        List<EntityField> entityFields = getEntityFieldOfEntity(entityName);
        return entityFields.stream()
                .collect(Collectors.toMap(
                        field -> field.getFieldName().toLowerCase(),
                        field -> field
                ));
    }
}
