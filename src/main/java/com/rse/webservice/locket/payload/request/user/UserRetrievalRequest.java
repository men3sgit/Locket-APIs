package com.rse.webservice.locket.payload.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserRetrievalRequest {
    private Long id;
}
