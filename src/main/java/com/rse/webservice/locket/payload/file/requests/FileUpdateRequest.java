package com.rse.webservice.locket.payload.file.requests;

import lombok.Data;

@Data
public class FileUpdateRequest {
    private String name;

    private String path;
}
