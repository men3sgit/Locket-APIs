package com.rse.webservice.locket.controller;


import com.rse.webservice.locket.payload.request.post.PostCreateRequest;
import com.rse.webservice.locket.payload.request.post.PostSelfRequest;
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
    public ApiResponse<?> create(@RequestBody PostCreateRequest request) {
        return new ApiResponse<>(postService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<?> self(@PathVariable(value = "id") Long id) {
        return new ApiResponse<>(postService.self(PostSelfRequest.of(id)));
    }



}
