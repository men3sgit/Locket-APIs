package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.constants.Const;
import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.TymPost;
import com.rse.webservice.locket.payload.tym.requests.TymCreateRequest;
import com.rse.webservice.locket.payload.tym.requests.TymSearchRequest;
import com.rse.webservice.locket.payload.tym.requests.TymUpdateRequest;
import com.rse.webservice.locket.payload.tym.responses.TymCreateResponse;
import com.rse.webservice.locket.payload.tym.responses.TymSearchResponse;
import com.rse.webservice.locket.payload.tym.responses.TymUpdateResponse;
import com.rse.webservice.locket.repository.TymRepository;
import com.rse.webservice.locket.service.TymService;
import com.rse.webservice.locket.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TymServiceImpl implements TymService {
    private final TymRepository tymRepository;
    @Override
    public TymCreateResponse create(TymCreateRequest request) {
        var tymOptional = tymRepository.findTymPostByAccountIdAndPostId(request.getAccountId(), request.getPostId());
        if(tymOptional.isPresent()){
            var existTym = tymOptional.get();
            existTym.setStatus(Const.GeneralStatus.ACTIVE);
            tymRepository.save(existTym);
            return TymCreateResponse.of(existTym.getId());
        }
        var newFollow = DataUtils.copyProperties(request, TymPost.class);
        tymRepository.save(newFollow);
        return TymCreateResponse.of(newFollow.getId());
    }

    @Override
    public TymUpdateResponse update(TymUpdateRequest request) {
        var tym = getTym(request.getId());
        var coppyTym = DataUtils.copyProperties(tym, TymPost.class);
        coppyTym.setStatus(Const.GeneralStatus.DELETED);
        tymRepository.save(coppyTym);
        return TymUpdateResponse.of(Boolean.TRUE);
    }

    @Override
    public List<TymSearchResponse> search(TymSearchRequest request) {
        var tyms = tymRepository.findTymPostByPostId(request.getPostId());
        return tyms.stream()
                .map(tym ->
                        DataUtils.copyProperties(tym, TymSearchResponse.class))
                .collect(Collectors.toList());
    }

    private TymPost getTym(Long id) {
        return tymRepository
                .findById(id)
                .orElseThrow(() -> new ApiRequestException("Tym with id %s not found".formatted(id)));
    }
}
