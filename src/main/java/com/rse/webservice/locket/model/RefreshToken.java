package com.rse.webservice.locket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "refresh_tokens")
public class RefreshToken extends AbstractAudit {
    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "expired_at", nullable = false)
    private Timestamp expiredAt;


}
