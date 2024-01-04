package com.rse.webservice.locket.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class DataUtils {

    public static final String PHONE_NUMBER_PATTERN = "^(([03+[2-9]|05+[6|8|9]|07+[0|6|7|8|9]|08+[1-9]|09+[1-4|6-9]]){3})+[0-9]{7}$";
    public static final String EMAIL_PATTERN = "[A-Z0-9a-z._%+-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]+";
    public static final String IMAGE_EXTENSION_PATTERN = "^(gif|jpe?g|tiff?|png|webp|bmp)$";
    public static final String REGEX_BASE64 = "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$";
    public static final String REGEX_FILE_NAME = "^.+\\.[A-Za-z]+$";
    public static final String REGEX_CRON_EXPRESSION = "^((\\*|\\?|\\d+((\\/|\\-){0,1}(\\d+))*)\\s*){6}$";

    public static final String UPPER_CASE_LETTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_CASE_LETTER = UPPER_CASE_LETTER.toLowerCase();
    public static final String DIGITS = "0123456789";
    public static final String SPECIAL_CHARACTERS = "!@#$%^&*()";


    public static <T> T copyProperties(Object source, Class<T> classTarget, String... ignoreProperties) {
        try {
            T target = classTarget.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target, ignoreProperties);
            return target;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException("Error copying properties", e);
        }
    }

    public static <T> T copyProperties(Object source, Class<T> classTarget) {
        return copyProperties(source, classTarget, (String[]) null);
    }

}

