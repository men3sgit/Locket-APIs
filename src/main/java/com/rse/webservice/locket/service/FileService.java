package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.file.requests.*;
import com.rse.webservice.locket.payload.file.responses.*;

public interface FileService {
    FileUploadResponse upload(FileUploadRequest request);

    FileSelfResponse self(FileSelfRequest request);

    FileSearchResponse search(FileSearchRequest request);

    FileDeleteResponse delete(FileDeleteRequest request);

    FileDataResponse getData(FileDataRequest request);

}
