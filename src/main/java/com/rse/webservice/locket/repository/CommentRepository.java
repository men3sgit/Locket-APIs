package com.rse.webservice.locket.repository;

import com.rse.webservice.locket.model.Account;
import com.rse.webservice.locket.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select * from comments where comments.status <> 2 and comments.post_id =:postId", nativeQuery = true)
    List<Comment> findByPostId(Long postId);
}
