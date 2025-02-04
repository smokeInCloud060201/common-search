package vn.com.demo.graphql.commons.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.tuple.Pair;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class ReflectionUtil {

    private static final String BASE_PACKAGE_NAME = "vn.com.demo.graphql";

    public Field[] getAllFieldOfClass(Class<?> clazz) {
        return clazz.getDeclaredFields();
    }

    public Field getFieldByName(Class<?> clazz, String fieldName) {
        Field[] fields = getAllFieldOfClass(clazz);

        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        return null;
    }

    public Annotation[] getAllAnnotationsOfClass(Class<?> clazz) {
        return clazz.getDeclaredAnnotations();
    }

    public static <T extends Annotation> T getAnnotationByName(Class<?> clazz, Class<T> annotationClass) {
        return Arrays.stream(clazz.getAnnotations())
                .filter(annotation -> annotation.annotationType().equals(annotationClass))
                .map(annotationClass::cast)
                .findFirst()
                .orElse(null);
    }

    public <T extends Annotation> List<Pair<T, Class<?>>> getPairsClassByAnnotation(Class<T> annotationClass) {
        if (annotationClass == null || !annotationClass.isAnnotation()) {
            return Collections.emptyList();
        }
        Reflections reflections = new Reflections(BASE_PACKAGE_NAME);
        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(annotationClass);

        return classSet.stream()
                .map(clazz -> Pair.<T, Class<?>>of(getAnnotationByName(clazz, annotationClass), clazz))
                .toList();
    }
}
