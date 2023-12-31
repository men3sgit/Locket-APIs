package com.rse.webservice.locket.model;

import com.rse.webservice.locket.constants.Const;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "posts")
public class Post extends AbstractAudit {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @Column(name = "content")
    private String content;

    @Column(name = "state")
    private Integer state = Const.GeneralState.PUBLIC;

    @Column(name = "view_count")
    private Integer viewCount = 0;

    @Column(name = "is_edit")
    private Boolean isEdit = Boolean.FALSE;


    public Boolean isEdit() {
        return Objects.nonNull(super.getUpdatedAt());
    }




}
