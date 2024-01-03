package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.payload.ApiResponse;
import com.rse.webservice.locket.payload.image.requests.*;
import com.rse.webservice.locket.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{id}/download")
    public ResponseEntity<ByteArrayResource> downloadById(@PathVariable Long id) {
        var request = ImageDownloadRequest.of(id);
        var response = imageService.download(request);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", response.getName());

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ByteArrayResource(response.getContent()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> getImageById(@PathVariable Long id) {
        var request = ImageDownloadRequest.of(id);
        var response = imageService.download(request);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.inline().filename(response.getName()).build());

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.IMAGE_JPEG)
                .body(new ByteArrayResource(response.getContent()));
    }

    @GetMapping("/{id}/self")
    public ApiResponse<?> self(@PathVariable Long id) {
        var request = ImageSelfRequest.of(id);
        var response = imageService.self(request);

        return new ApiResponse<>(response);
    }

    @PostMapping(path = {"", "/upload"})
    public ApiResponse<?> upload(@RequestPart MultipartFile file) {
        var request = ImageUploadRequest.of(file);
        var response = imageService.upload(request);

        return new ApiResponse<>(response);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable Long id) {
        var request = ImageDeleteRequest.of(id);
        var response = imageService.delete(request);

        return new ApiResponse<>(response);
    }

    @PutMapping("/{id}")
    public ApiResponse<?> update(@PathVariable Long id, @RequestPart MultipartFile file) {
        var request = ImageUpdateRequest.of(id, file);
        var response = imageService.update(request);

        return new ApiResponse<>(response);
    }


}
