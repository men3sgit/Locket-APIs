package com.rse.webservice.locket.payload.post.responses;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostSelfResponse {

    private Long id;

    private String imagePath;

    private String content;

    private String author;

    private Integer state;

    private Integer viewCount;

    private Boolean isEdit;

    private Timestamp publishedAt;


}
