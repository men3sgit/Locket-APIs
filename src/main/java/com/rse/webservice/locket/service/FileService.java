package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.file.requests.FileDeleteRequest;
import com.rse.webservice.locket.payload.file.requests.FileSearchRequest;
import com.rse.webservice.locket.payload.file.requests.FileSelfRequest;
import com.rse.webservice.locket.payload.file.requests.FileUploadRequest;
import com.rse.webservice.locket.payload.file.responses.FileDeleteResponse;
import com.rse.webservice.locket.payload.file.responses.FileSearchResponse;
import com.rse.webservice.locket.payload.file.responses.FileSelfResponse;
import com.rse.webservice.locket.payload.file.responses.FileUploadResponse;

public interface FileService {
    FileUploadResponse upload(FileUploadRequest request);

    FileSelfResponse self(FileSelfRequest request);

    FileSearchResponse search(FileSearchRequest request);

    FileDeleteResponse delete(FileDeleteRequest request);
}
