package com.rse.webservice.locket.payload.image.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor(staticName = "of")
public class ImageUploadRequest {
    private MultipartFile file;
}
