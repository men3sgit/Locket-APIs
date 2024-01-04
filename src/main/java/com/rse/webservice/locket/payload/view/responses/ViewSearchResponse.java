package com.rse.webservice.locket.payload.view.responses;

import com.rse.webservice.locket.payload.common.responses.AuditResponse;
import lombok.Data;

@Data
public class ViewSearchResponse extends AuditResponse {
    private Long storyId;
    private Long accountId;
}
