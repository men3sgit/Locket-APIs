package com.rse.webservice.locket.model;

import com.rse.webservice.locket.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tokens")
public class Token extends AbstractAudit {
    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "token_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "expired_at", nullable = false)
    private Timestamp expiredAt;

    @Column(name = "revoke_at")
    private Timestamp revokedAt;


    public boolean isTokenValid() {
        return !isTokenUsed() && !isTokenExpired();
    }

    public boolean isTokenUsed() {
        return Objects.nonNull(revokedAt);
    }

    public boolean isTokenExpired() {
        return Objects.nonNull(expiredAt) && expiredAt.before(new Date());
    }

    public void setRevokedAt(Timestamp revokedAt) {
        this.revokedAt = revokedAt;
        setExpiredAt(revokedAt);
    }

    public void revoke() {
        setRevokedAt(new Timestamp(System.currentTimeMillis()));
    }

}
