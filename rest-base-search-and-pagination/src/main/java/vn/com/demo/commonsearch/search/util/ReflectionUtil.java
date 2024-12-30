package vn.com.demo.commonsearch.search.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectionUtil {

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

    public Annotation getAnnotationByName(Class<?> clazz, String annotationName) {
        Annotation[] annotations = getAllAnnotationsOfClass(clazz);
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().getName().equals(annotationName)) {
                return annotation;
            }
        }
        return null;
    }
}
