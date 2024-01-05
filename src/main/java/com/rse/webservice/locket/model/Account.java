package com.rse.webservice.locket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Table(name = "accounts")
@Entity
@Data
public class Account extends AbstractAudit {
    private Long userId;

    private String appName;

    private String avatarPath;

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate dob;

    private String phoneNumber;

    private transient int age;


}
