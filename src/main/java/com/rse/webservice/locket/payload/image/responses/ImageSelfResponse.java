package com.rse.webservice.locket.payload.image.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageSelfResponse {
    private Long id;

    private String name;

    private String path;

    private String description;

    private String formattedFileSize;

    private String extension;

    private Integer width;

    private Integer height;


}
