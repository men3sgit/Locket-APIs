package com.rse.webservice.locket.config.storage.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.rse.webservice.locket.config.storage.StorageService;
import com.rse.webservice.locket.constants.URL;
import com.rse.webservice.locket.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    @Value("${locket.app.storage.bucket.name}")
    private String bucketName;

    private final AmazonS3 amazonS3;

    @Override
    public String uploadFile(MultipartFile file) {
        File fileObj = FileUtils.convertMultipartFileToFile(file);
        String fileName = FileUtils.generateNewFileName(fileObj, System.currentTimeMillis() + "_" + file.getOriginalFilename());
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return fileName;
    }

    @Override
    public byte[] downloadFile(String fileName) {
        return new byte[0];
    }

    @Override
    public boolean deleteFile(String fileName) {
        return false;
    }
}
