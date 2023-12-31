package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.constants.ConstantKey;
import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.User;
import com.rse.webservice.locket.model.VerificationToken;
import com.rse.webservice.locket.payload.request.auth.AuthenticationRequest;
import com.rse.webservice.locket.payload.request.auth.RegistrationRequest;
import com.rse.webservice.locket.payload.response.auth.AuthenticationResponse;
import com.rse.webservice.locket.payload.response.auth.RegistrationResponse;
import com.rse.webservice.locket.repository.UserRepository;
import com.rse.webservice.locket.repository.VerificationTokenRepository;
import com.rse.webservice.locket.security.jwt.impl.JwtServiceImpl;
import com.rse.webservice.locket.service.AuthenticationService;
import com.rse.webservice.locket.service.VerificationService;
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
    private final JwtServiceImpl jwtUtils;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final VerificationTokenRepository verificationTokenRepository;
    private final VerificationService verificationService;


    //TODO: Refactoring can use phone number or something else
    @Override
    public RegistrationResponse addNewUser(RegistrationRequest request) {
        // create new user
        if (userRepository.findByUsername(request.getEmail()).isPresent()) {
            throw new ApiRequestException(ConstantKey.MSG_EMAIL_TAKEN);
        }
        var newUser = DataUtils.copyProperties(request, User.class);
        newUser.setUsername(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        // create new token
        var verificationToken = new VerificationToken(newUser.getId());
        verificationTokenRepository.save(verificationToken);

        verificationService.sendMailToVerify(request.getEmail(), request.getName(), verificationToken.getToken());

        return RegistrationResponse.of(newUser.getId());
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            var userDetails = userDetailsService.loadUserByUsername(request.getEmail()); // Check user exists
            var authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            authenticationManager.authenticate(authentication);// check enable user or user locked

            String token = jwtUtils.generateToken(userDetails);

            return AuthenticationResponse.of(token);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

    }


}
