package com.rse.webservice.locket.payload.image.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ImageDeleteRequest {
    private Long id;
}
