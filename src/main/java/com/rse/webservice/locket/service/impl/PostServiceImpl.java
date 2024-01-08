package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.enums.MediaState;
import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.Post;
import com.rse.webservice.locket.payload.account.requests.AccountSelfRequest;
import com.rse.webservice.locket.payload.image.requests.ImageUploadRequest;
import com.rse.webservice.locket.payload.post.requests.*;
import com.rse.webservice.locket.payload.post.responses.PostCreateResponse;
import com.rse.webservice.locket.payload.post.responses.PostSearchResponse;
import com.rse.webservice.locket.payload.post.responses.PostSelfResponse;
import com.rse.webservice.locket.payload.post.responses.PostUpdateResponse;
import com.rse.webservice.locket.repository.PostRepository;
import com.rse.webservice.locket.service.AccountService;
import com.rse.webservice.locket.service.ImageService;
import com.rse.webservice.locket.service.PostService;
import com.rse.webservice.locket.service.CommonService;
import com.rse.webservice.locket.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CommonService commonService;
    private final AccountService accountService;
    private final ImageService imageService;


    @Override
    public PostCreateResponse create(PostCreateRequest request) {
        var newPost = DataUtils.copyProperties(request, Post.class);
        var uploadResponse = imageService.upload(ImageUploadRequest.of(request.getMultipartFile()));
        newPost.setImagePath(uploadResponse.getPath());
        newPost.setMediaState(MediaState.fromString(request.getMediaState()));
        newPost.setAccountId(commonService.getCurrentAccountId());
        postRepository.save(newPost);
        return PostCreateResponse.of(newPost.getId());
    }

    @Override
    public PostSelfResponse self(PostSelfRequest request) {
        var post = getPost(request.getId());
        post.setViewCount(post.getViewCount() + 1);
        postRepository.save(post);

        var account = accountService.self(AccountSelfRequest.of(post.getAccountId()));
        String author = account.getFirstName() + " " + account.getLastName();
        var response = DataUtils.copyProperties(post, PostSelfResponse.class);
        response.setAuthor(author);
        response.setPublishedAt(post.getCreatedAt());
        return response;
    }

    @Override
    public PostSelfResponse delete(PostDeleteRequest request) {
        return null;
    }

    @Override
    public PostSearchResponse search(PostSearchRequest request) {
        return null;
    }

    @Override
    public PostUpdateResponse update(PostUpdateRequest request) {
        return null;
    }

    @Override
    public List<PostSelfResponse> getPostsByAccountId(Long accountId) {
        return postRepository.findAllByAccountId(accountId)
                .stream()
                .map(post -> {
                    var postResponse = DataUtils.copyProperties(post, PostSelfResponse.class);
                        postResponse.setState(post.getMediaState());
                    return postResponse;
                })
                .collect(Collectors.toList());
    }

    private Post getPost(Long id) {
        return postRepository
                .findById(id)
                .orElseThrow(() -> new ApiRequestException("post with id %s not found".formatted(id)));
    }

}
