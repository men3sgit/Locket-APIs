package com.rse.webservice.locket.repository;

import com.rse.webservice.locket.model.Follow;
import com.rse.webservice.locket.model.TymPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TymRepository extends JpaRepository<TymPost, Long> {
    @Query(value  = "select * from tymsposts where tymsposts.account_id =:accountId and tymsposts.post_id =:postId", nativeQuery = true)
    Optional<TymPost> findTymPostByAccountIdAndPostId(Long accountId, Long postId);

    @Query(value = "select * from tymsposts where tymsposts.status <> 2 and tymsposts.post_id =:postId", nativeQuery = true)
    List<Follow> findTymPostByPostId(Long postId);

}
