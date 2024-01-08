package com.rse.webservice.locket.payload.post.responses;

import com.rse.webservice.locket.enums.MediaState;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostSelfResponse {

    private Long id;

    private String imagePath;

    private String content;

    private String author;

    private String imageAuthorPath; // app name

    private MediaState state;

    private Integer viewCount;

    private Boolean isEdit;

    private Timestamp publishedAt;

    private Boolean isPin;

    private Boolean isLike;


}
