package com.rse.webservice.locket.payload.post.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PostCreateResponse {
    private Long id;
}
