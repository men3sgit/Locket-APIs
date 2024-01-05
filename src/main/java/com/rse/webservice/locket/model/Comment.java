package com.rse.webservice.locket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "comments")
public class Comment extends AbstractAudit {

    private Long postId;

    private String content;

    private Long accountId;

    private Long currentCommentId;

}
