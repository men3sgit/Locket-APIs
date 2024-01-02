package com.rse.webservice.locket.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Table(name = "verification_tokens")
@Entity
@Data
@NoArgsConstructor
public class VerificationToken extends AbstractAudit {
    @Transient
    private static final long EXPIRATION = 1800000L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "expired_at")
    private Timestamp expiredAt;

    @Column(name = "verified_at")
    private Timestamp verifiedAt;

    public VerificationToken(Long userId) {
        this.token = UUID.randomUUID().toString();
        this.userId = userId;
        this.expiredAt = new Timestamp(System.currentTimeMillis() + EXPIRATION);
    }

    public boolean isTokenValid() {
        return !isTokenUsed() && !isTokenExpired();
    }

    public boolean isTokenUsed() {
        return Objects.nonNull(verifiedAt);
    }

    public boolean isTokenExpired() {
        return Objects.nonNull(expiredAt) && expiredAt.before(new Date());
    }

    public void setVerifiedAt(Timestamp verifiedAt) {
        this.verifiedAt = verifiedAt;
        setExpiredAt(verifiedAt);
    }

    public void verify() {
        setVerifiedAt(new Timestamp(System.currentTimeMillis()));
    }


}