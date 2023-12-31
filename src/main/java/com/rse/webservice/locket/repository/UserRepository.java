package com.rse.webservice.locket.repository;

import com.rse.webservice.locket.model.User;
import com.rse.webservice.locket.constants.Const;
import com.rse.webservice.locket.model.expand.UsernameHandler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE status <> " + Const.GeneralStatus.DELETED + " AND username = :username", nativeQuery = true)
    Optional<User> findByUsername(String username);


    @Query(value = "SELECT * FROM users WHERE status <> " + Const.GeneralStatus.DELETED + " AND id = :id", nativeQuery = true)
    Optional<User> findById(Long id);

    @Query(value = "SELECT * FROM users WHERE status <> " + Const.GeneralStatus.DELETED, nativeQuery = true)
    List<User> findAll();

}
