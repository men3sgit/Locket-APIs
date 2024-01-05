package com.rse.webservice.locket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tymsposts")
public class TymPost extends AbstractAudit{
    private Long postId;
    private Long accountId;
}
