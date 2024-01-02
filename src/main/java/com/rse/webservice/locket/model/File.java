package com.rse.webservice.locket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "files")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class File extends AbstractAudit {

    private String name;

    private String path;

    private Long size;

    private String extension;

    @PostPersist
    public void setPath() {
        this.setPath(path + this.getId());
    }

}