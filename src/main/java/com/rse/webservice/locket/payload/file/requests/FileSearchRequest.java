package com.rse.webservice.locket.payload.file.requests;

import lombok.Data;

@Data
public class FileSearchRequest {
    private Long userId;

    private String name;
}
