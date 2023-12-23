package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.User;
import com.rse.webservice.locket.repository.UserRepository;
import com.rse.webservice.locket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ApiRequestException("User not found"));
    }


}
