package com.rse.webservice.locket.payload.tym.responses;

import com.rse.webservice.locket.payload.common.responses.AuditResponse;
import lombok.Data;

@Data
public class TymSearchResponse extends AuditResponse {

    private Long postId;
    private Long accountId;
}
