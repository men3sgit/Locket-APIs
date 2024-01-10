package com.rse.webservice.locket.test.solid.o2;

import java.lang.reflect.Field;

public class JSONResponse implements FormatterResponse {


    @Override
    public String format(Data data) {
        Class<?> clazz = data.getClass();
        Field[] fields = clazz.getDeclaredFields();

        StringBuilder response = new StringBuilder();
        response.append("{\n");

        for (Field field : fields) {
            field.setAccessible(true); // Allow access to private fields

            try {
                Object value = field.get(data);
                response.append(String.format("  \"%s\": \"%s\",\n", field.getName(), value));
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // Handle the exception appropriately in a real application
            }
        }

        // Remove the trailing comma if there are fields
        if (fields.length > 0) {
            response.setLength(response.length() - 2);
        }

        response.append("\n}");
        return response.toString();
    }
}
