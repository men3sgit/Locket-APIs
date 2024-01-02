package com.rse.webservice.locket.payload.file.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class FileSearchResponse {
    private List<FileSelfResponse> list;
}
