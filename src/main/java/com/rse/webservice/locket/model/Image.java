package com.rse.webservice.locket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "images")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image extends AbstractAudit {

    private Long fileId;

    private String description;

    private Integer width;

    private Integer height;
}
