package com.rse.webservice.locket.payload.comment.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class CommentUpdateRequest {
    private Long id;

    private Long postId;

    private String content;

    private Long accountId;

    private Long currentCommentId;
}
