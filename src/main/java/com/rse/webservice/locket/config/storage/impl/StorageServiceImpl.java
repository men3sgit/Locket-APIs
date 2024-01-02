package com.rse.webservice.locket.config.storage.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.rse.webservice.locket.config.storage.StorageService;
import com.rse.webservice.locket.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFile(String fileName) {
        amazonS3.deleteObject(bucketName,fileName);
    }
}
