package com.rse.webservice.locket.payload.file.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class FileUploadResponse {
    private Long id;
    private String path;
}
