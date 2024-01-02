package com.rse.webservice.locket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "images")
public class Image extends AbstractAudit {

    private Long fileId;

    private String name;

    private String description;

    private String format;

    private Long size;

    private Integer width;

    private Integer height;
}
