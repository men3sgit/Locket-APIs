package com.rse.webservice.locket.payload.response.post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PostDeleteResponse {
    private Boolean success;
}
