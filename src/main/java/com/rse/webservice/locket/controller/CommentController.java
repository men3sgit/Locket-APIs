package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.payload.ApiResponse;
import com.rse.webservice.locket.payload.comment.requests.*;
import com.rse.webservice.locket.payload.post.requests.*;
import com.rse.webservice.locket.service.CommentService;
import com.rse.webservice.locket.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ApiResponse<?> create(@RequestBody CommentCreateRequest request) {
        var response = commentService.create(request);
        return new ApiResponse<>(response);
    }

    @GetMapping("/{id}")
    public ApiResponse<?> self(@PathVariable Long id) {
        var response = commentService.self(CommentSelfRequest.of(id));
        return new ApiResponse<>(response);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable Long id) {
        var response = commentService.delete(CommentDeleteRequest.of(id));
        return new ApiResponse<>(response);
    }

    @GetMapping(path = {"/", ""})
    public ApiResponse<?> search(@RequestBody CommentSearchRequest request) {
        var response = commentService.search(request);
        return new ApiResponse<>(response);
    }

    @PutMapping("/{id}")
    public ApiResponse<?> update(@PathVariable Long id,  @RequestBody CommentUpdateRequest request) {
        request.setId(id);
        var response = commentService.update(request);
        return new ApiResponse<>(response);
    }

}
