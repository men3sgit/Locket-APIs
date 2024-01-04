package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.View;
import com.rse.webservice.locket.payload.tym.responses.TymSearchResponse;
import com.rse.webservice.locket.payload.view.requests.ViewCreateRequest;
import com.rse.webservice.locket.payload.view.requests.ViewSearchRequest;
import com.rse.webservice.locket.payload.view.requests.ViewUpdateRequest;
import com.rse.webservice.locket.payload.view.responses.ViewCreateResponse;
import com.rse.webservice.locket.payload.view.responses.ViewSearchResponse;
import com.rse.webservice.locket.payload.view.responses.ViewUpdateResponse;
import com.rse.webservice.locket.repository.ViewRepository;
import com.rse.webservice.locket.service.ViewService;
import com.rse.webservice.locket.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViewServiceImpl implements ViewService {
    private final ViewRepository viewRepository;
    @Override
    public ViewCreateResponse create(ViewCreateRequest request) {
        var viewOptional = viewRepository.findViewByAccountIdAndStoryId(request.getAccountId(), request.getStoryId());
        if(viewOptional.isPresent()){
            var existview = viewOptional.get();
            return ViewCreateResponse.of(existview.getId());
        }
        var newView = DataUtils.copyProperties(request, View.class);
        viewRepository.save(newView);
        return ViewCreateResponse.of(newView.getId());
    }

    @Override
    public ViewUpdateResponse update(ViewUpdateRequest request) {
        var viewOptional = viewRepository.findViewByAccountIdAndStoryId(request.getAccountId(), request.getStoryId());
        var existview = viewOptional.get();
        existview.setInteractKind(request.getInteractKind());
        viewRepository.save(existview);
        return ViewUpdateResponse.of(Boolean.TRUE);
    }

    @Override
    public List<ViewSearchResponse> search(ViewSearchRequest request) {
        var views = viewRepository.findViewByStoryId(request.getStoryId());
        return views.stream()
                .map(view ->
                        DataUtils.copyProperties(view, ViewSearchResponse.class))
                .collect(Collectors.toList());
    }

}
