package com.rse.webservice.locket.repository;

import com.rse.webservice.locket.enums.TokenType;
import com.rse.webservice.locket.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {

    Optional<Token> findByTokenAndTokenType(String token, TokenType tokenType);

    Optional<Token> findByToken(String token);
}
