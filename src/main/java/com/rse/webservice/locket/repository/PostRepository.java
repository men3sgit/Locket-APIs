package com.rse.webservice.locket.repository;

import com.rse.webservice.locket.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByAccountId(Long accountId);

}
