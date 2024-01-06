package com.rse.webservice.locket.payload.account.responses;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountSelfResponse {

    private Long userId;

    private String email;

    private String avatarPath;

    private String appName;

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate dob;

    private String phoneNumber;

    private String bio;

    private int age;
}
