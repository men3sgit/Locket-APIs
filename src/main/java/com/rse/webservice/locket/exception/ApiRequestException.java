package com.rse.webservice.locket.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiRequestException extends RuntimeException {
    private int code;
    private String message;
    private Collection<Object> errorFields;
    private String type;

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Collection<Object> errorFields) {
        super(message);
        this.errorFields = errorFields.stream().filter(Objects::nonNull).collect(Collectors.toList());

    }

    public ApiRequestException(String message, int code, Collection<Object> errorFields) {
        this(message, errorFields);
        this.code = code;

    }

}