package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.User;
import com.rse.webservice.locket.payload.user.requests.UserSelfRequest;
import com.rse.webservice.locket.payload.user.responses.UserSelfResponse;
import com.rse.webservice.locket.repository.UserRepository;
import com.rse.webservice.locket.service.UserService;
import com.rse.webservice.locket.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public UserSelfResponse self(UserSelfRequest request) {
        var user = getUserById(request.getId());
        return DataUtils.copyProperties(user, UserSelfResponse.class);
    }

    @Override
    public List<UserSelfResponse> search() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> DataUtils.copyProperties(user, UserSelfResponse.class))
                .collect(Collectors.toList());
    }

    private User getUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ApiRequestException("User not found"));
    }
}
