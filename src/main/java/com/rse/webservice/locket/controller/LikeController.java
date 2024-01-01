package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.payload.like.requests.LikeCreateRequest;
import com.rse.webservice.locket.payload.like.requests.LikeDeleteRequest;
import com.rse.webservice.locket.payload.like.requests.LikeSearchRequest;
import com.rse.webservice.locket.payload.like.requests.LikeSelfRequest;
import com.rse.webservice.locket.payload.ApiResponse;
import com.rse.webservice.locket.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}/{accountId}")
    public ApiResponse<?> create(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "accountId") Long accountId) {

        var request = new LikeCreateRequest(postId, accountId);
        var response = likeService.create(request);
        return new ApiResponse<>(response);
    }

    @DeleteMapping("/{postId}/{accountId}")
    public ApiResponse<?> delete(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "accountId") Long accountId) {

        var request = LikeDeleteRequest.builder()
                .postId(postId)
                .accountId(accountId)
                .build();

        var response = likeService.delete(request);
        return new ApiResponse<>(response);
    }

    @GetMapping("/{id}")
    public ApiResponse<?> self(@PathVariable Long id) {
        var request = LikeSelfRequest.of(id);
        var response = likeService.self(request);
        return new ApiResponse<>(response);
    }

    @GetMapping
    public ApiResponse<?> search(LikeSearchRequest request){
        var response = likeService.search(request);
        return new ApiResponse<>(response);
    }
}
