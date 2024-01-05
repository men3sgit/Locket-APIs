package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.payload.ApiResponse;
import com.rse.webservice.locket.payload.tym.requests.TymCreateRequest;
import com.rse.webservice.locket.payload.tym.requests.TymSearchRequest;
import com.rse.webservice.locket.payload.tym.requests.TymUpdateRequest;
import com.rse.webservice.locket.service.TymService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tyms")
public class TymController {

    private final TymService tymService;

    @PostMapping("/tym")
    public ApiResponse<?> create(@RequestBody TymCreateRequest request) {
        var response = tymService.create(request);
        return new ApiResponse<>(response);
    }

    @PostMapping("/untym")
    public ApiResponse<?> update(@RequestBody TymUpdateRequest request) {
        var response = tymService.update(request);
        return new ApiResponse<>(response);
    }

    @GetMapping("/search")
    public ApiResponse<?> search(@RequestBody TymSearchRequest request) {
        var response = tymService.search(request);
        return new ApiResponse<>(response);
    }
}
