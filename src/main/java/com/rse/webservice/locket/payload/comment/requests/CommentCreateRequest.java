package com.rse.webservice.locket.payload.comment.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
public class CommentCreateRequest {

    private Long postId;

    private String content;

    private Long accountId;

    private Long currentCommentId;

}
