package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.payload.ApiResponse;
import com.rse.webservice.locket.payload.file.requests.FileUploadRequest;
import com.rse.webservice.locket.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/files")
public class FileController {
    private final FileService fileService;

    @GetMapping("/{id}")
    public ApiResponse<?> self(@PathVariable Long id) {
        var response = "";
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
}




