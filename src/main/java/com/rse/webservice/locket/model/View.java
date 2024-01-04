package com.rse.webservice.locket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "views")
public class View extends AbstractAudit {
    private Long storyId;

    private Long accountId;

    private String interactKind;
}
