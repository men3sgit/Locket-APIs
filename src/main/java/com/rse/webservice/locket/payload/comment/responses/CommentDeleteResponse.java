package com.rse.webservice.locket.payload.comment.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class CommentDeleteResponse {
    private Boolean success;
}
