package com.rse.webservice.locket.controller;

import com.rse.webservice.locket.constants.HttpStatusCodes;
import com.rse.webservice.locket.payload.ApiResponse;
import com.rse.webservice.locket.payload.pin.requests.PinCreateRequest;
import com.rse.webservice.locket.service.CommonService;
import com.rse.webservice.locket.service.PinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pins")
public class PinController {
    private final PinService pinService;
    private final CommonService commonService;

    @GetMapping
    public ApiResponse<?> getAllPin() {
        return new ApiResponse<>(null);
    }

    @PostMapping
    public ApiResponse<?> create(@RequestBody PinCreateRequest request) {
        var response = pinService.create(request);
        return new ApiResponse<>(response);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable Long id) {
        pinService.delete(id);
        return new ApiResponse<>("Success", HttpStatusCodes.NO_CONTENT);
    }
}
