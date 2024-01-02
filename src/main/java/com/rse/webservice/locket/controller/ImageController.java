package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.payload.file.requests.FileDataRequest;
import com.rse.webservice.locket.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
//    TODO: private final ImageService imageService;
    private final FileService fileService;
    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> getImageById(@PathVariable Long id){
        var response = fileService.getData(FileDataRequest.of(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", response.getName());

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ByteArrayResource(response.getContent()));
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<ByteArrayResource> downloadById(@PathVariable Long id){
        var response = fileService.getData(FileDataRequest.of(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.inline().filename(response.getName()).build());

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.IMAGE_JPEG)
                .body(new ByteArrayResource(response.getContent()));
    }

}
