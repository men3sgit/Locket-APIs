package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.Pin;
import com.rse.webservice.locket.payload.pin.requests.PinCreateRequest;
import com.rse.webservice.locket.payload.pin.response.PinCreateResponse;
import com.rse.webservice.locket.repository.PinRepository;
import com.rse.webservice.locket.service.PinService;
import com.rse.webservice.locket.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PinServiceImpl implements PinService {
    private final PinRepository pinRepository;

    @Override
    public PinCreateResponse create(PinCreateRequest request) {
        var newPin = DataUtils.copyProperties(request, Pin.class);
        pinRepository.save(newPin);
        return PinCreateResponse.of(newPin.getId());
    }

    @Override
    public void delete(Long id) {
        var storedPin = getPin(id);
        pinRepository.delete(storedPin);
    }
    private Pin getPin(Long id){
        return pinRepository.findById(id).orElseThrow(() -> new ApiRequestException("Pin doesn't exist"));
    }
}
