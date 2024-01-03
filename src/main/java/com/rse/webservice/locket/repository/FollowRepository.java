package com.rse.webservice.locket.repository;

import com.rse.webservice.locket.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query(value  = "select * from follows where follows.account_id =:accountId and follows.following_id =:followingId", nativeQuery = true)
    Optional<Follow> findFollowsByAccountIdAndFollowingId(Long accountId, Long followingId);

    @Query(value = "select * from follows where follows.status <> 2 and follows.account_id =:accountId", nativeQuery = true)
    List<Follow> findFollowsByAccountId(Long accountId);

    @Query(value = "select * from follows where follows.status <> 2 and follows.following_id =:followingId", nativeQuery = true)
    List<Follow> findFollowsByFollowingId(Long followingId);
}
