package com.rse.webservice.locket.model;

import com.rse.webservice.locket.enums.MediaState;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "posts")
public class Post extends AbstractAudit {

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @Column(name = "content")
    private String content;

    @Column(name = "media_state")
    @Enumerated(EnumType.STRING)
    private MediaState mediaState = MediaState.PUBLIC;

    @Column(name = "view_count")
    private Integer viewCount = 0;

    @Column(name = "is_edit")
    private Boolean isEdit = Boolean.FALSE;


    public Boolean isEdit() {
        return Objects.nonNull(super.getUpdatedAt());
    }


}
