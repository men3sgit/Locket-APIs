package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.payload.ApiResponse;
import com.rse.webservice.locket.payload.tym.requests.TymCreateRequest;
import com.rse.webservice.locket.payload.tym.requests.TymSearchRequest;
import com.rse.webservice.locket.payload.tym.requests.TymUpdateRequest;
import com.rse.webservice.locket.payload.view.requests.ViewCreateRequest;
import com.rse.webservice.locket.payload.view.requests.ViewSearchRequest;
import com.rse.webservice.locket.payload.view.requests.ViewUpdateRequest;
import com.rse.webservice.locket.service.TymService;
import com.rse.webservice.locket.service.ViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/views")
public class ViewController {

    private final ViewService viewService;

    @PostMapping("/view")
    public ApiResponse<?> create(@RequestBody ViewCreateRequest request) {
        var response = viewService.create(request);
        return new ApiResponse<>(response);
    }

    @PostMapping("/interact")
    public ApiResponse<?> update(@RequestBody ViewUpdateRequest request) {
        var response = viewService.update(request);
        return new ApiResponse<>(response);
    }

    @GetMapping("/search")
    public ApiResponse<?> search(@RequestBody ViewSearchRequest request) {
        var response = viewService.search(request);
        return new ApiResponse<>(response);
    }
}
