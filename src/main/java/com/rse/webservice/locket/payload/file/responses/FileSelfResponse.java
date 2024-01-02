package com.rse.webservice.locket.payload.file.responses;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FileSelfResponse {
    private Long id;

    private String name;

    private String path;

    private String formattedSize;

    private String extension;

    private Timestamp createdAt;

}
