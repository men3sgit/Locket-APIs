package com.rse.webservice.locket.payload.image.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ImageDownloadResponse {
    private byte[] content;
    private String name;
}
