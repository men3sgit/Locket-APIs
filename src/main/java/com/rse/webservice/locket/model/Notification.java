package com.rse.webservice.locket.model;

import com.rse.webservice.locket.enums.NotificationType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "notifications")
public class Notification extends AbstractAudit {

    private Long userId;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private String message;

    private Boolean read;


}
