package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.model.Like;
import com.rse.webservice.locket.payload.like.requests.LikeCreateRequest;
import com.rse.webservice.locket.payload.like.requests.LikeDeleteRequest;
import com.rse.webservice.locket.payload.like.requests.LikeSearchRequest;
import com.rse.webservice.locket.payload.like.requests.LikeSelfRequest;
import com.rse.webservice.locket.payload.like.responses.LikeCreateResponse;
import com.rse.webservice.locket.payload.like.responses.LikeDeleteResponse;
import com.rse.webservice.locket.payload.like.responses.LikeSearchResponse;
import com.rse.webservice.locket.payload.like.responses.LikeSelfResponse;
import com.rse.webservice.locket.repository.LikeRepository;
import com.rse.webservice.locket.service.LikeService;
import com.rse.webservice.locket.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;

    @Override
    public LikeCreateResponse create(LikeCreateRequest request) {
        // TODO: maybe contain an exception duplicate 2 likes
        var newLike = DataUtils.copyProperties(request, Like.class);
        likeRepository.save(newLike);
        return LikeCreateResponse.of(newLike.getId());
    }

    @Override
    public LikeDeleteResponse delete(LikeDeleteRequest request) {
        return null;
    }

    @Override
    public LikeSearchResponse search(LikeSearchRequest request) {
        return null;
    }

    @Override
    public LikeSelfResponse self(LikeSelfRequest request) {
        return null;
    }
}
