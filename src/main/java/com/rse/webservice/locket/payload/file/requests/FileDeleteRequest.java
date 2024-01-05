package com.rse.webservice.locket.payload.file.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class FileDeleteRequest {
    private Long id;
}
