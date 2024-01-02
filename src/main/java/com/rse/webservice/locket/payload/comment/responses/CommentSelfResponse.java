package com.rse.webservice.locket.payload.comment.responses;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentSelfResponse {

    private Long id;

    private Long postId;

    private String content;

    private Long accountId;

    private Long currentCommentId;
}
