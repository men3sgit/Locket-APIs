package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.constants.Const;
import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.Follow;
import com.rse.webservice.locket.payload.follow.requests.FollowByMeSearchRequest;
import com.rse.webservice.locket.payload.follow.requests.FollowCreateRequest;
import com.rse.webservice.locket.payload.follow.requests.FollowSearchRequest;
import com.rse.webservice.locket.payload.follow.requests.FollowUpdateRequest;
import com.rse.webservice.locket.payload.follow.responses.FollowByMeSearchResponse;
import com.rse.webservice.locket.payload.follow.responses.FollowCreateResponse;
import com.rse.webservice.locket.payload.follow.responses.FollowSearchResponse;
import com.rse.webservice.locket.payload.follow.responses.FollowUpdateResponse;
import com.rse.webservice.locket.repository.FollowRepository;
import com.rse.webservice.locket.service.FollowService;
import com.rse.webservice.locket.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;

    @Override
    public FollowCreateResponse create(FollowCreateRequest request) {
        var followOptional = followRepository.findFollowsByAccountIdAndFollowingId(request.getAccountId(), request.getFollowingId());
        if(followOptional.isPresent()){
            var existFollow = followOptional.get();
            existFollow.setStatus(Const.GeneralStatus.ACTIVE);
            followRepository.save(existFollow);
            return FollowCreateResponse.of(existFollow.getId());
        }
        var newFollow = DataUtils.copyProperties(request, Follow.class);
        followRepository.save(newFollow);
        return FollowCreateResponse.of(newFollow.getId());
    }

    @Override
    public FollowUpdateResponse update(FollowUpdateRequest request) {
        var follow = getFollow(request.getId());
        var coppyFollow = DataUtils.copyProperties(follow, Follow.class);
        coppyFollow.setStatus(Const.GeneralStatus.DELETED);
        followRepository.save(coppyFollow);
        return FollowUpdateResponse.of(Boolean.TRUE);
    }

    @Override
    public List<FollowSearchResponse> search(FollowSearchRequest request) {
        var follows = followRepository.findFollowsByAccountId(request.getAccountId());
        return follows.stream()
                .map(follow ->
                        DataUtils.copyProperties(follow, FollowSearchResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FollowByMeSearchResponse> search(FollowByMeSearchRequest request) {
        var follows = followRepository.findFollowsByFollowingId(request.getFollowingId());
        return follows.stream()
                .map(follow ->
                        DataUtils.copyProperties(follow, FollowByMeSearchResponse.class))
                .collect(Collectors.toList());
    }

    private Follow getFollow(Long id) {
        return followRepository
                .findById(id)
                .orElseThrow(() -> new ApiRequestException("follow with id %s not found".formatted(id)));
    }
}
