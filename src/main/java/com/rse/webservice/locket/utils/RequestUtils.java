package com.rse.webservice.locket.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtils {
    private RequestUtils() {
        // Private constructor to prevent instantiation
    }

    public static String getRequestUrl() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            return request.getRequestURL().toString();
        }
        return null;
    }
}
