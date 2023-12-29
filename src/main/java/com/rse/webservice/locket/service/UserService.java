package com.rse.webservice.locket.service;

import com.rse.webservice.locket.payload.request.user.UserCreateRequest;
import com.rse.webservice.locket.payload.response.user.UserRetrievalResponse;

import java.util.List;

public interface UserService {

    UserRetrievalResponse retrieveUserById(UserCreateRequest request);

    // TODO: Paging list
    List<UserRetrievalResponse> retrieveAllUsers();

}
