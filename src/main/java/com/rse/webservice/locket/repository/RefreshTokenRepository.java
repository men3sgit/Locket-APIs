package com.rse.webservice.locket.repository;

import com.rse.webservice.locket.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<Token, Long> {
    
    Optional<Token> findByUserId(Long userId);

    Optional<Token> findByToken(String token);


}
