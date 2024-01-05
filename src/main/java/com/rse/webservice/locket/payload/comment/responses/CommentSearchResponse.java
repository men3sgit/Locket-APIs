package com.rse.webservice.locket.payload.comment.responses;

import com.rse.webservice.locket.model.AbstractAudit;
import com.rse.webservice.locket.payload.common.responses.AuditResponse;
import lombok.Data;

@Data
public class CommentSearchResponse extends AuditResponse {

    private Long postId;

    private String content;

    private Long accountId;

    private Long currentCommentId;


}
