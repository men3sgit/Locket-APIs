package com.rse.webservice.locket.payload.post.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PostDeleteRequest {
    private Long id;
}
