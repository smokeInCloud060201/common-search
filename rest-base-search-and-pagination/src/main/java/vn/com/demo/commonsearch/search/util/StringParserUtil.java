package vn.com.demo.commonsearch.search.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static vn.com.demo.commonsearch.constants.DateTimeConstants.LOCAL_DATE_TIME_FORMAT;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringParserUtil {

    public static Object parseStringToObjectByType(String valueStr, Class<?> typeClass) {
        if (Long.class.equals(typeClass) || long.class.equals(typeClass)) {
            return parseLong(valueStr);
        } else if (Double.class.equals(typeClass) || double.class.equals(typeClass)
                || Float.class.equals(typeClass) || float.class.equals(typeClass)) {
            return parseDouble(valueStr);
        }
        else if (LocalDateTime.class.equals(typeClass)) {
            return parseDateTime(valueStr);
        } else if (Boolean.class.equals(typeClass) || boolean.class.equals(typeClass)) {
            return parseBoolean(valueStr);
        } else if (Enum.class.isAssignableFrom(typeClass)) {
            return parseEnum(valueStr, typeClass);
        } else if (String.class.equals(typeClass)) {
            return valueStr.toUpperCase();
        } else {
            //Unknown type
            return valueStr;
        }
    }


    public static Long parseLong(String valueStr) {
        try {
            return Long.parseLong(valueStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid numeric value: " + valueStr, e);
        }
    }

    public static Double parseDouble(String valueStr) {
        try {
            return Double.parseDouble(valueStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid numeric value: " + valueStr, e);
        }
    }

    public static LocalDateTime parseDateTime(String valueStr) {
        try {
            return LocalDateTime.parse(valueStr, DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date-time format: " + valueStr, e);
        }
    }

    public static Boolean parseBoolean(String valueStr) {
        return Boolean.parseBoolean(valueStr);
    }

    @SuppressWarnings("unchecked")
    public static Enum<?> parseEnum(String valueStr, Class<?> typeClass) {
        try {
            Class<Enum> enumClass = (Class<Enum>) typeClass;
            return Enum.valueOf(enumClass, valueStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid enum value: " + valueStr, e);
        }
    }
}
