package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.model.Like;
import com.rse.webservice.locket.payload.request.like.LikeCreateRequest;
import com.rse.webservice.locket.payload.request.like.LikeDeleteRequest;
import com.rse.webservice.locket.payload.request.like.LikeSearchRequest;
import com.rse.webservice.locket.payload.request.like.LikeSelfRequest;
import com.rse.webservice.locket.payload.response.like.LikeCreateResponse;
import com.rse.webservice.locket.payload.response.like.LikeDeleteResponse;
import com.rse.webservice.locket.payload.response.like.LikeSearchResponse;
import com.rse.webservice.locket.payload.response.like.LikeSelfResponse;
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
