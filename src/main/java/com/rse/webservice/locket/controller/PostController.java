package com.rse.webservice.locket.controller;


import com.rse.webservice.locket.payload.request.post.PostCreateRequest;
import com.rse.webservice.locket.payload.request.post.PostDeleteRequest;
import com.rse.webservice.locket.payload.request.post.PostSearchRequest;
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
        var response = postService.create(request);
        return new ApiResponse<>(response);
    }

    @GetMapping("/{id}")
    public ApiResponse<?> self(@PathVariable(value = "id") Long id) {
        var response = postService.self(PostSelfRequest.of(id));
        return new ApiResponse<>(response);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable(value = "id") Long id) {
        var response = postService.delete(PostDeleteRequest.of(id));
        return new ApiResponse<>(response);
    }

    @GetMapping(path = {"/", ""})
    public ApiResponse<?> search(PostSearchRequest request) {
        return new ApiResponse<>("");
    }


}
