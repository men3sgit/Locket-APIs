package com.rse.webservice.locket.service.common;

import org.springframework.security.core.userdetails.UserDetails;

public interface CommonService {

    UserDetails userDetails();

    Long getLoginId();

    String getLoginUsername();

    Boolean existsUser(Long userId);
}
