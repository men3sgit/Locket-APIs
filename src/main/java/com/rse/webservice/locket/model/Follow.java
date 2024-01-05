package com.rse.webservice.locket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "follows")
public class Follow extends AbstractAudit{
    private Long accountId;

    private Long followingId;

}
