package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.constants.ConstantKey;
import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.User;
import com.rse.webservice.locket.payload.account.requests.AccountCreateRequest;
import com.rse.webservice.locket.payload.auth.requests.AuthenticationRequest;
import com.rse.webservice.locket.payload.auth.requests.RegistrationRequest;
import com.rse.webservice.locket.payload.auth.responses.AuthenticationResponse;
import com.rse.webservice.locket.payload.auth.responses.LogoutResponse;
import com.rse.webservice.locket.payload.auth.responses.RegistrationResponse;
import com.rse.webservice.locket.payload.token.requests.TokenCreateRequest;
import com.rse.webservice.locket.repository.UserRepository;
import com.rse.webservice.locket.security.jwt.JwtService;
import com.rse.webservice.locket.service.*;
import com.rse.webservice.locket.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final AccountService accountService;
    private final CommonService commonService;


    //TODO: Refactoring can use phone number or something else
    @Override
    public RegistrationResponse addNewUser(RegistrationRequest request) {
        // create new user
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ApiRequestException(ConstantKey.MSG_EMAIL_TAKEN);
        }
        // create new user
        var newUser = DataUtils.copyProperties(request, User.class);
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);

        //create new account
        String appName = request.getEmail().substring(0,request.getEmail().indexOf('@'));
        var accountCreateRequest = AccountCreateRequest.builder()
                .userId(newUser.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .appName(appName)
                .phoneNumber(request.getPhoneNumber())
                .build();
        accountService.create(accountCreateRequest);

        // create new token
        var tokenCreateResponse = tokenService.createRegistrationToken(TokenCreateRequest.of(newUser.getId()));
        tokenService.sendMailToVerify(request.getEmail(), accountCreateRequest.getFirstName(), tokenCreateResponse.getToken());

        return RegistrationResponse.of(newUser.getId());
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            var userDetails = userDetailsService.loadUserByUsername(request.getEmail()); // Check user exists
            var authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            authenticationManager.authenticate(authentication);// check enable user or user locked

            String token = jwtService.generateToken(userDetails);
            Long userId = userRepository.findByEmail(userDetails.getUsername()).get().getId();

            return AuthenticationResponse.of(token);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

    }

    @Override
    public LogoutResponse logout() {
        var loginUsername = commonService.getLoginUsername();

        return null;
    }


}
