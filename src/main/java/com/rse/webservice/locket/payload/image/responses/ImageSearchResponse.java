package com.rse.webservice.locket.payload.image.responses;

import lombok.Data;
import org.springframework.data.domain.Page;


@Data
public class ImageSearchResponse {
    private Page<ImageSelfResponse> list;
}
