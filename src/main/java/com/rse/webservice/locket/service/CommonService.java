package com.rse.webservice.locket.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface CommonService {

    UserDetails userDetails();

    Long getLoginId();

    Long getCurrentAccountId();

    String getLoginUsername();

    Boolean existsUser(Long userId);


    Boolean isNoLoginAccount(Long accountId);

    Boolean isNoLogin();
}
