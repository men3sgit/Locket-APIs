package com.rse.webservice.locket.utils;

import java.lang.reflect.Field;

public class DataUtils {
    public static <T> T copyFields(Object source, Class<T> destinationClass) {
        T destination = null;

        try {
            destination = destinationClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace(); // Handle exceptions based on your requirements
        }

        if (destination != null) {
            Class<?> sourceClass = source.getClass();

            for (Field sourceField : sourceClass.getDeclaredFields()) {
                try {
                    // Access private fields
                    sourceField.setAccessible(true);

                    Field destinationField = destinationClass.getDeclaredField(sourceField.getName());
                    destinationField.setAccessible(true);

                    // Copy the value from source to destination
                    Object value = sourceField.get(source);
                    destinationField.set(destination, value);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace(); // Handle exceptions based on your requirements
                }
            }
        }

        return destination;
    }
}
