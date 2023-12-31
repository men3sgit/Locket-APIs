package com.rse.webservice.locket.payload.account.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class AccountUpdateRequest {
    private Long accountId;

    private String bio;

    private String appName;

    private MultipartFile avatar;

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate dob;

    private String phoneNumber;
}
