package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.payload.file.requests.FileDeleteRequest;
import com.rse.webservice.locket.payload.file.requests.FileSearchRequest;
import com.rse.webservice.locket.payload.file.requests.FileSelfRequest;
import com.rse.webservice.locket.payload.file.requests.FileUploadRequest;
import com.rse.webservice.locket.payload.file.responses.FileDeleteResponse;
import com.rse.webservice.locket.payload.file.responses.FileSearchResponse;
import com.rse.webservice.locket.payload.file.responses.FileSelfResponse;
import com.rse.webservice.locket.payload.file.responses.FileUploadResponse;
import com.rse.webservice.locket.service.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public FileUploadResponse upload(FileUploadRequest request) {
        return null;
    }

    @Override
    public FileSelfResponse self(FileSelfRequest request) {
        return null;
    }

    @Override
    public FileSearchResponse search(FileSearchRequest request) {
        return null;
    }

    @Override
    public FileDeleteResponse delete(FileDeleteRequest request) {
        return null;
    }
}
