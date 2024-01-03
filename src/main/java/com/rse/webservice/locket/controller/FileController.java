package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.payload.ApiResponse;
import com.rse.webservice.locket.payload.file.requests.*;
import com.rse.webservice.locket.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/files")
public class FileController {
    private final FileService fileService;

    @GetMapping("/{id}/download")
    public ResponseEntity<?> download(@PathVariable Long id) {
        var request = FileDownloadRequest.of(id);
        var response = fileService.download(request);
        // Set response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", response.getName());

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ByteArrayResource(response.getContent()));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable Long id) {
        var request = FileDeleteRequest.of(id);
        var response = fileService.delete(request);

        return new ApiResponse<>(response);
    }

    @PostMapping
    public ApiResponse<?> upload(@RequestPart MultipartFile file) {
        var request = FileUploadRequest.builder()
                .file(file)
                .build();
        var response = fileService.upload(request);

        return new ApiResponse<>(response);
    }

    @GetMapping("/{id}")
    public ApiResponse<?> self(@PathVariable Long id) {
        var request = FileSelfRequest.of(id);
        var response = fileService.self(request);

        return new ApiResponse<>(response);
    }

    @GetMapping
    public ApiResponse<?> search(FileSearchRequest request) {
        var response = fileService.search(request);
        return new ApiResponse<>(response);
    }

}




