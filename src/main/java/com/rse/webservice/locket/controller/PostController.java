package com.rse.webservice.locket.controller;


import com.rse.webservice.locket.payload.request.post.*;
import com.rse.webservice.locket.payload.response.ApiResponse;
import com.rse.webservice.locket.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ApiResponse<?> create(PostCreateRequest request) {
        var response = postService.create(request);
        return new ApiResponse<>(response);
    }

    @GetMapping("/{id}")
    public ApiResponse<?> self(@PathVariable Long id) {
        var response = postService.self(PostSelfRequest.of(id));
        return new ApiResponse<>(response);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable Long id) {
        var response = postService.delete(PostDeleteRequest.of(id));
        return new ApiResponse<>(response);
    }

    @GetMapping(path = {"/", ""})
    public ApiResponse<?> search(PostSearchRequest request) {
        var response = postService.search(request);
        return new ApiResponse<>(response);
    }

    @PutMapping("/{id}")
    public ApiResponse<?> update(@PathVariable Long id, PostUpdateRequest request) {
        request.setId(id);
        var response = postService.update(request);
        return new ApiResponse<>(response);
    }


}
