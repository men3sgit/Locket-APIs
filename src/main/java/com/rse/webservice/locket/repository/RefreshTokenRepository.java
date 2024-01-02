package com.rse.webservice.locket.repository;

import com.rse.webservice.locket.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    
    Optional<RefreshToken> findByUserId(Long userId);

    Optional<RefreshToken> findByToken(String token);


}
