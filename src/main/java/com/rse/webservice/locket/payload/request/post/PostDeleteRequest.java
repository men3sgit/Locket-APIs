package com.rse.webservice.locket.payload.request.post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PostDeleteRequest {
    private Long id;
}
