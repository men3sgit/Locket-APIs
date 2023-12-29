package com.rse.webservice.locket.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class DataUtils {

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

