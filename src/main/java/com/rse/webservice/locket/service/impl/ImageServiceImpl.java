package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.constants.Const;
import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.Image;
import com.rse.webservice.locket.payload.file.requests.*;
import com.rse.webservice.locket.payload.image.requests.*;
import com.rse.webservice.locket.payload.image.responses.*;
import com.rse.webservice.locket.repository.ImageRepository;
import com.rse.webservice.locket.service.FileService;
import com.rse.webservice.locket.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final FileService fileService;

    @Override
    public ImageUpdateResponse update(ImageUpdateRequest request) {
        var storedImage = getImage(request.getId());
        // Update the associated file using the FileService
        fileService.update(new FileUpdateRequest(storedImage.getFileId(), request.getFile()));
        // Update additional properties for the stored image
        updateImageProperties(storedImage, request.getFile());
        imageRepository.save(storedImage);

        return ImageUpdateResponse.of(storedImage.getId());
    }

    @Override
    public ImageUploadResponse upload(ImageUploadRequest request) {
        var multipartFile = Objects.requireNonNull(request.getFile(), "File must not be null or empty");
        var fileUploadResponse = fileService.upload(FileUploadRequest.of(multipartFile));

        var newImage = Image.builder()
                .fileId(fileUploadResponse.getId())
                .build();
        updateImageProperties(newImage, multipartFile);
        imageRepository.save(newImage);

        String formattedImagePath = fileUploadResponse.getPath().replace("/api/v1/files", "/api/v1/images");
        formattedImagePath = formattedImagePath.substring(0, formattedImagePath.lastIndexOf('/') + 1) + newImage.getId();
        return ImageUploadResponse.of(formattedImagePath);
    }

    private void updateImageProperties(Image image, MultipartFile multipartFile) {
        BufferedImage bufferedImage = readImageFromFile(multipartFile);
        // Update image properties
        image.setWidth(bufferedImage.getWidth());
        image.setHeight(bufferedImage.getHeight());
        image.setDescription(multipartFile.getContentType());

    }


    @Override
    public ImageDeleteResponse delete(ImageDeleteRequest request) {
        // check image exists
        var storedImage = getImage(request.getId());
        fileService.delete(FileDeleteRequest.of(storedImage.getFileId()));
        storedImage.setStatus(Const.GeneralStatus.DELETED);
        imageRepository.save(storedImage);

        return ImageDeleteResponse.of(Boolean.TRUE);
    }


    @Override
    public ImageDownloadResponse download(ImageDownloadRequest request) {
        var storedImage = getImage(request.getId());
        var fileDownloadRequest = FileDownloadRequest.of(storedImage.getFileId());
        var downloadedFile = fileService.download(fileDownloadRequest);
        return ImageDownloadResponse.of(downloadedFile.getContent(), downloadedFile.getName());
    }


    private BufferedImage readImageFromFile(MultipartFile multipartFile) {
        try {
            return ImageIO.read(multipartFile.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ImageSelfResponse self(ImageSelfRequest request) {
        var storedImage = getImage(request.getId());
        var fileSelf = fileService.self(FileSelfRequest.of(storedImage.getFileId()));

        return ImageSelfResponse.builder()
                .id(storedImage.getId())
                .extension(fileSelf.getExtension())
                .path(fileSelf.getPath())
                .name(fileSelf.getName())
                .formattedFileSize(fileSelf.getFormattedSize())
                .height(storedImage.getHeight())
                .width(storedImage.getWidth())
                .build();
    }

    @Override
    public ImageSearchResponse search(ImageSearchRequest request) {
        return null;
    }

    private Image getImage(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Image does not exist with id " + id));

    }

}
