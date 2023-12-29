package com.rse.webservice.locket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "posts")
public class Post extends AbstractAudit {

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name = "state")
    private Integer state;

    @Column(name = "view_count")
    private Integer viewCount = 0;

}
