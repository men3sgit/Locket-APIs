package com.rse.webservice.locket.payload.response.account;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountSelfResponse {

    private Long userId;

    private String avatarPath;

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate dob;

    private String phoneNumber;

    private int age;
}
