package com.rse.webservice.locket.model;

import com.rse.webservice.locket.utils.Const;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "posts")
public class Post extends AbstractAudit {

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @Column(name = "content")
    private String content;

    @Column(name = "author")
    private String author;

    @Column(name = "state")
    private Integer state = Const.GeneralState.PUBLIC;

    @Column(name = "view_count")
    private Integer viewCount = 0;

}
