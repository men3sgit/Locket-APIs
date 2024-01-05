package com.rse.webservice.locket.payload.file.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor(staticName = "of")
public class FileUploadRequest {
    private MultipartFile file;
}
