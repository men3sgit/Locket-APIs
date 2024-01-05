package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.image.requests.*;
import com.rse.webservice.locket.payload.image.responses.*;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ImageUpdateResponse update(ImageUpdateRequest request);

    ImageDeleteResponse delete(ImageDeleteRequest request);

    ImageUploadResponse upload(ImageUploadRequest request);

    ImageDownloadResponse download(ImageDownloadRequest request);

    ImageSelfResponse self(ImageSelfRequest request);

    ImageSearchResponse search(ImageSearchRequest request);


}
