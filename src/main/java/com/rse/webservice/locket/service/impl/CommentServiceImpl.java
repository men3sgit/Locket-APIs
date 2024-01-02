package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.constants.Const;
import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.Comment;
import com.rse.webservice.locket.model.Post;
import com.rse.webservice.locket.payload.comment.requests.*;
import com.rse.webservice.locket.payload.comment.responses.*;
import com.rse.webservice.locket.payload.post.responses.PostSelfResponse;
import com.rse.webservice.locket.repository.CommentRepository;
import com.rse.webservice.locket.service.CommentService;
import com.rse.webservice.locket.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public CommentCreateResponse create(CommentCreateRequest request) {
        var newComment = DataUtils.copyProperties(request, Comment.class);
        commentRepository.save(newComment);
        return CommentCreateResponse.of(newComment.getId());
    }

    @Override
    public CommentSelfResponse self(CommentSelfRequest request) {
        var newComment = getComment(request.getId());
        var response = DataUtils.copyProperties(newComment, CommentSelfResponse.class);
        return response;
    }

    @Override
    public CommentDeleteResponse delete(CommentDeleteRequest request) {
        var storedComment = getComment(request.getId());
        storedComment.setStatus(Const.GeneralStatus.DELETED);
        commentRepository.save(storedComment);
        return CommentDeleteResponse.of(Boolean.TRUE);
    }

    @Override
    public List<CommentSearchResponse> search(CommentSearchRequest request) {
        var comments = commentRepository.findByPostId(request.getPostId());
        return comments.stream()
                .map(comment ->
                        DataUtils.copyProperties(comment, CommentSearchResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentUpdateResponse update(CommentUpdateRequest request) {
        var storedComment = getComment(request.getId());
        var response = DataUtils.copyProperties(request, storedComment.getClass());
        commentRepository.save(response);
        return CommentUpdateResponse.of(Boolean.TRUE);
    }

    private Comment getComment(Long id) {
        return commentRepository
                .findById(id)
                .orElseThrow(() -> new ApiRequestException("comment with id %s not found".formatted(id)));
    }
}
