package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.payload.ApiResponse;
import com.rse.webservice.locket.payload.follow.requests.FollowByMeSearchRequest;
import com.rse.webservice.locket.payload.follow.requests.FollowCreateRequest;
import com.rse.webservice.locket.payload.follow.requests.FollowSearchRequest;
import com.rse.webservice.locket.payload.follow.requests.FollowUpdateRequest;
import com.rse.webservice.locket.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/follows")
public class FollowController {
    private final FollowService followService;

    @PostMapping("follow")
    public ApiResponse<?> create(@RequestBody FollowCreateRequest request) {
        var response = followService.create(request);
        return new ApiResponse<>(response);
    }

    @PostMapping("/unfollow")
    public ApiResponse<?> update(@RequestBody FollowUpdateRequest request) {
        var response = followService.update(request);
        return new ApiResponse<>(response);
    }

    @GetMapping("/search")
    public ApiResponse<?> search(@RequestBody FollowSearchRequest request) {
        var response = followService.search(request);
        return new ApiResponse<>(response);
    }

    @GetMapping("/searchbyme")
    public ApiResponse<?> search(@RequestBody FollowByMeSearchRequest request) {
        var response = followService.search(request);
        return new ApiResponse<>(response);
    }
}
