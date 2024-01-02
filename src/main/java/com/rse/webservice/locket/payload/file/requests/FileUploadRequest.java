package com.rse.webservice.locket.payload.file.requests;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class FileUploadRequest {
    private MultipartFile file;
}
