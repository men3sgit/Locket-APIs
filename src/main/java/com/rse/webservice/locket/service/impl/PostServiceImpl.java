package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.constants.ConstantKey;
import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.Post;
import com.rse.webservice.locket.payload.request.post.PostCreateRequest;
import com.rse.webservice.locket.payload.request.post.PostDeleteRequest;
import com.rse.webservice.locket.payload.request.post.PostSelfRequest;
import com.rse.webservice.locket.payload.response.post.PostCreateResponse;
import com.rse.webservice.locket.payload.response.post.PostSelfResponse;
import com.rse.webservice.locket.repository.PostRepository;
import com.rse.webservice.locket.service.PostService;
import com.rse.webservice.locket.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public PostCreateResponse create(PostCreateRequest request) {
        var newPost = DataUtils.copyProperties(request, Post.class);
        postRepository.save(newPost);
        return PostCreateResponse.of(newPost.getId());
    }

    @Override
    public PostSelfResponse self(PostSelfRequest request) {
        return DataUtils.copyProperties(getPost(request.getId()), PostSelfResponse.class);
    }

    @Override
    public PostSelfResponse delete(PostDeleteRequest request) {
        return null;
    }

    private Post getPost(Long id) {
        return postRepository
                .findById(id)
                .orElseThrow(() -> new ApiRequestException("post with id %s not found".formatted(id)));
    }

}
