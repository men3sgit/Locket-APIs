package com.rse.webservice.locket.test.solid.o2;

import java.lang.reflect.Field;

public class XMLResponse implements FormatterResponse {

    @Override
    public String format(Data data) {
        Class<?> clazz = data.getClass();
        Field[] fields = clazz.getDeclaredFields();

        StringBuilder response = new StringBuilder();
        response.append("<data>\n");

        for (Field field : fields) {
            field.setAccessible(true); // Allow access to private fields

            try {
                Object value = field.get(data);
                response.append(String.format("  <%s>%s</%s>\n", field.getName(), value, field.getName()));
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // Handle the exception appropriately in a real application
            }
        }

        response.append("</data>");
        return response.toString();
    }
}
