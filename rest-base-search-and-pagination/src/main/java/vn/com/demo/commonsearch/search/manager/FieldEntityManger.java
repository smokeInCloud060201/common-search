package vn.com.demo.commonsearch.search.manager;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import vn.com.demo.commonsearch.base.BaseEntity;
import vn.com.demo.commonsearch.search.dto.EntityField;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FieldEntityManger {
    private static final String BASE_PACKAGE_LOADER = "vn.com.demo.commonsearch";

    @Cacheable(cacheNames = "fieldEntityCache")
    public Map<Class<? extends BaseEntity>, List<EntityField>> getFieldsOfEntity() {
        Set<Class<? extends BaseEntity>> entities = getEntityClass();
        return entities.stream().collect(Collectors.toMap(k -> k,
                this::getAllEntityField));
    }

    private Set<Class<? extends BaseEntity>> getEntityClass() {
        log.info("getEntityClass start");
        Reflections reflections = new Reflections(BASE_PACKAGE_LOADER);
        return reflections.getSubTypesOf(BaseEntity.class);
    }

    private List<Field> getAllField(Class<? extends BaseEntity> entityClass) {
        List<Field> currentFields = new ArrayList<>(Arrays.stream(entityClass.getDeclaredFields()).toList());
        currentFields.addAll(Arrays.stream(entityClass.getSuperclass().getDeclaredFields())
                .filter(f -> Modifier.isPublic(f.getModifiers()) || Modifier.isProtected(f.getModifiers()))
                .toList());
        return currentFields;
    }

    private List<EntityField> getAllEntityField(Class<? extends BaseEntity> entityClass) {
        List<Field> fields = getAllField(entityClass);
        return fields.stream().map(EntityField::new).toList();
    }
}
