package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.request.user.UserSelfRequest;
import com.rse.webservice.locket.payload.response.user.UserSelfResponse;

import java.util.List;

public interface UserService {

    UserSelfResponse self(UserSelfRequest request);

    // TODO: Paging list
    List<UserSelfResponse> search();

}
