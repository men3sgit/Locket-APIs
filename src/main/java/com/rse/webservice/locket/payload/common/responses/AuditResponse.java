package com.rse.webservice.locket.payload.common.responses;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@Data
public class AuditResponse {

    private Long id;


    private Integer status;


    private Timestamp createdAt;


    private Timestamp updatedAt;

    private Long createdBy;


    private Long updatedBy;
}
