package com.rse.webservice.locket.repository;

import com.rse.webservice.locket.model.Follow;
import com.rse.webservice.locket.model.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ViewRepository extends JpaRepository<View, Long> {
    @Query(value  = "select * from views where views.account_id =:accountId and views.story_id =:storyId", nativeQuery = true)
    Optional<View> findViewByAccountIdAndStoryId(Long accountId, Long storyId);

    @Query(value = "select * from views where views.status <> 2 and views.story_id =:storyId", nativeQuery = true)
    List<View> findViewByStoryId(Long storyId);

}
