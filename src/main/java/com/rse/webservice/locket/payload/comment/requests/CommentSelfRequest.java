package com.rse.webservice.locket.payload.comment.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class CommentSelfRequest {
    private Long id;
}
