package com.rse.webservice.locket.repository;

import com.rse.webservice.locket.model.VerificationToken;
import com.rse.webservice.locket.utils.Const;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    @Query(value = "SELECT user_id FROM verification_tokens WHERE status <> " + Const.GeneralStatus.DELETED + " AND token = :token", nativeQuery = true)
    Long getUserIdByToken(String token);

    @Query(value = "SELECT * FROM verification_tokens WHERE status <> " + Const.GeneralStatus.DELETED + " AND token = :token", nativeQuery = true)
    Optional<VerificationToken> findByToken(String token);

}
