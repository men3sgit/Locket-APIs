package com.rse.webservice.locket.payload.request.post;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostCreateRequest {
    private MultipartFile multipartFile;

    private String content;

}
