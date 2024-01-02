package com.rse.webservice.locket.config.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String uploadFile(MultipartFile file);

    byte[] downloadFile(String fileName);

    boolean deleteFile(String fileName);


}
