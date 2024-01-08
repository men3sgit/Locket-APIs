package com.rse.webservice.locket.controller;


import com.rse.webservice.locket.constants.HttpStatusCodes;
import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.payload.post.requests.*;
import com.rse.webservice.locket.payload.ApiResponse;
import com.rse.webservice.locket.service.CommonService;
import com.rse.webservice.locket.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;
    private final CommonService commonService;


    @PostMapping
    public ApiResponse<?> create(PostCreateRequest request) {
        var response = postService.create(request);
        return new ApiResponse<>(response, HttpStatusCodes.CREATED);
    }

    @GetMapping("/{id}")
    public ApiResponse<?> self(@PathVariable Long id) {
        var currentAccountId = commonService.getCurrentAccountId();
        var response = postService.self(PostSelfRequest.of(id, currentAccountId));
        return new ApiResponse<>(response);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable Long id) {
        var response = postService.delete(PostDeleteRequest.of(id));
        return new ApiResponse<>(response);
    }

    @GetMapping(path = {"/search"})
    public ApiResponse<?> search(PostSearchRequest request) {
        var response = postService.search(request);
        return new ApiResponse<>(response);
    }

    @GetMapping
    public ApiResponse<?> getALlPosts() {
        var currentAccountId = commonService.getCurrentAccountId();
        if (commonService.isNoLoginAccount(currentAccountId)) {
            throw new ApiRequestException("Please login");
        }

        var response = postService.getPostsByAccountId(currentAccountId);
        return new ApiResponse<>(response);
    }


    @PutMapping
    public ApiResponse<?> update(PostUpdateRequest request) {
        var response = postService.update(request);
        return new ApiResponse<>(response);
    }


}
