package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.config.storage.StorageService;
import com.rse.webservice.locket.constants.Const;
import com.rse.webservice.locket.constants.URL;
import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.File;
import com.rse.webservice.locket.payload.file.requests.*;
import com.rse.webservice.locket.payload.file.responses.*;
import com.rse.webservice.locket.repository.FileRepository;
import com.rse.webservice.locket.service.FileService;
import com.rse.webservice.locket.utils.DataUtils;
import com.rse.webservice.locket.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {
    private static final String STORED_FILE_ENDPOINT = URL.DOMAIN + "/api/v1/files/";
    private final FileRepository fileRepository;
    private final StorageService storageService;


    @Override
    public FileUploadResponse upload(FileUploadRequest request) {
        var multipartFile = request.getFile();
        String fileName = storageService.uploadFile(multipartFile);
        var newFile = File.builder()
                .name(fileName)
                .path(STORED_FILE_ENDPOINT)
                .size(multipartFile.getSize())
                .extension(FileUtils.getFileExtension(Objects.requireNonNull(multipartFile.getOriginalFilename())))
                .build();

        fileRepository.save(newFile);
        return FileUploadResponse.of(newFile.getId());
    }

    @Override
    public FileSelfResponse self(FileSelfRequest request) {
        var storedFile = getFile(request.getId());
        var response = DataUtils.copyProperties(storedFile, FileSelfResponse.class);
        response.setFormattedSize(FileUtils.getFileSizeFormatted(storedFile.getSize()));
        return response;
    }

    @Override
    public FileSearchResponse search(FileSearchRequest request) {
        return null;
    }

    @Override
    public FileDeleteResponse delete(FileDeleteRequest request) {
        var storedFile = getFile(request.getId());
        storageService.deleteFile(storedFile.getName());
        // delete in DB
        storedFile.setStatus(Const.GeneralStatus.DELETED);
        fileRepository.save(storedFile);
        return FileDeleteResponse.of(Boolean.TRUE);
    }

    @Override
    public FileDataResponse getData(FileDataRequest request) {
        var storedFile = getFile(request.getId());
        var bytesContent = storageService.downloadFile(storedFile.getName());
        return FileDataResponse.of(bytesContent,storedFile.getName());
    }

    private File getFile(Long id) {
        return fileRepository.findById(id).orElseThrow(() -> new ApiRequestException("File does not exist with id " + id));

    }

}
