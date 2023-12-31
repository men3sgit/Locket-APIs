package com.rse.webservice.locket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "likes")
public class Like extends AbstractAudit {
    private Long postId;

    private Long accountId;
}
