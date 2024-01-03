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
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {
    private static final String STORED_FILE_PATH = URL.DOMAIN + "/api/v1/files/";
    private final FileRepository fileRepository;
    private final StorageService storageService;

    @Override
    public FileUploadResponse upload(FileUploadRequest request) {
        var multipartFile = request.getFile();
        var newFile = createFileEntity(multipartFile);
        fileRepository.save(newFile);
        return FileUploadResponse.of(newFile.getId());
    }

    @Override
    public FileUpdateResponse update(FileUpdateRequest request) {
        var storedFile = getFile(request.getId());
        storageService.deleteFile(storedFile.getName());
        var updatedFile = createFileEntity(request.getFile());
        updatedFile.setId(storedFile.getId()); // Override stored entity
        fileRepository.save(updatedFile);
        return FileUpdateResponse.of(updatedFile.getId());
    }

    private File createFileEntity(MultipartFile multipartFile) {
        String fileName = storageService.uploadFile(multipartFile);
        String[] typeAndExtension = Objects.requireNonNull(multipartFile.getContentType()).split("/");

        return File.builder()
                .name(fileName)
                .path(STORED_FILE_PATH)
                .size(multipartFile.getSize())
                .type(typeAndExtension[0])
                .extension(typeAndExtension[1])
                .build();
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
    public FileDownloadResponse download(FileDownloadRequest request) {
        var storedFile = getFile(request.getId());
        var bytesContent = storageService.downloadFile(storedFile.getName());
        return FileDownloadResponse.of(bytesContent, storedFile.getName());
    }


    private File getFile(Long id) {
        return fileRepository.findById(id).orElseThrow(() -> new ApiRequestException("File does not exist with id " + id));

    }

}
