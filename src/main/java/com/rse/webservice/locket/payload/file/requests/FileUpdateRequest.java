package com.rse.webservice.locket.payload.file.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class FileUpdateRequest {
    private Long id;
    private MultipartFile file;
}
