package com.rse.webservice.locket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pins")
public class Pin extends AbstractAudit {

    private Long accountId;
    private Long postId;

}
