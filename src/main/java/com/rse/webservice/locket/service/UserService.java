package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.user.requests.UserSelfRequest;
import com.rse.webservice.locket.payload.user.responses.UserSelfResponse;

import java.util.List;

public interface UserService {

    UserSelfResponse self(UserSelfRequest request);

    // TODO: Paging list
    List<UserSelfResponse> search();

}
