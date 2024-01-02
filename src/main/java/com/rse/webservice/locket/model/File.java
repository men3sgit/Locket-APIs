package com.rse.webservice.locket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "files")
public class File extends AbstractAudit {

    private String name;

    private String path;

    private Long size;

    private String extension;

}