package com.rse.webservice.locket.payload.request.post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PostSelfRequest {
    private Long id;
}
