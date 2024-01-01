package com.rse.webservice.locket.payload.post.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostCreateRequest {
    private MultipartFile multipartFile;

    private String content;

}
