package com.rse.webservice.locket.payload.account.requests;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AccountCreateRequest {
    private Long userId;

    private String avatarPath;

    private String appName;

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate dob;

    private String phoneNumber;
}
