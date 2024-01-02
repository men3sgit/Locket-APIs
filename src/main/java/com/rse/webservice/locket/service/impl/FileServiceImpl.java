package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.config.storage.StorageService;
import com.rse.webservice.locket.constants.URL;
import com.rse.webservice.locket.model.File;
import com.rse.webservice.locket.payload.file.requests.FileDeleteRequest;
import com.rse.webservice.locket.payload.file.requests.FileSearchRequest;
import com.rse.webservice.locket.payload.file.requests.FileSelfRequest;
import com.rse.webservice.locket.payload.file.requests.FileUploadRequest;
import com.rse.webservice.locket.payload.file.responses.FileDeleteResponse;
import com.rse.webservice.locket.payload.file.responses.FileSearchResponse;
import com.rse.webservice.locket.payload.file.responses.FileSelfResponse;
import com.rse.webservice.locket.payload.file.responses.FileUploadResponse;
import com.rse.webservice.locket.repository.FileRepository;
import com.rse.webservice.locket.service.FileService;
import com.rse.webservice.locket.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {
    private static final String DOWNLOAD_PATH = URL.DOMAIN + "/api/v1/files/download/";
    private final FileRepository fileRepository;
    private final StorageService storageService;


    @Override
    public FileUploadResponse upload(FileUploadRequest request) {
        var multipartFile = request.getFile();
        String fileName = storageService.uploadFile(multipartFile);
        var newFile = File.builder()
                .name(fileName)
                .path(DOWNLOAD_PATH + fileName)
                .size(multipartFile.getSize())
                .extension(FileUtils.getFileExtension(Objects.requireNonNull(multipartFile.getOriginalFilename())))
                .build();
        fileRepository.save(newFile);
        return FileUploadResponse.of(newFile.getId());
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
