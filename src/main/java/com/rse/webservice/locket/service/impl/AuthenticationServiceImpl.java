package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.Role;
import com.rse.webservice.locket.model.User;
import com.rse.webservice.locket.model.VerificationToken;
import com.rse.webservice.locket.payload.request.auth.AuthenticationRequest;
import com.rse.webservice.locket.payload.request.RegistrationRequest;
import com.rse.webservice.locket.payload.response.auth.AuthenticationResponse;
import com.rse.webservice.locket.payload.response.auth.RegistrationResponse;
import com.rse.webservice.locket.repository.UserRepository;
import com.rse.webservice.locket.repository.VerificationTokenRepository;
import com.rse.webservice.locket.security.jwt.JwtUtils;
import com.rse.webservice.locket.service.AuthenticationService;
import com.rse.webservice.locket.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final VerificationTokenRepository verificationTokenRepository;
    private final VerificationService verificationService;

    @Override
    public RegistrationResponse addNewUser(RegistrationRequest request) {
        // create new user
        var newUser = new User();
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ApiRequestException("Email taken");
        }
        BeanUtils.copyProperties(request, newUser);
        newUser.setRole(Role.USER);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);

        // create new token
        var verificationToken = new VerificationToken(newUser.getId());
        verificationTokenRepository.save(verificationToken);

        // send mail to verify
        verificationService.sendMailToVerify(request.getEmail(), verificationToken.getToken());
        return RegistrationResponse.of(newUser.getId());
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        var authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        try {
            authenticationManager.authenticate(authentication).isAuthenticated();
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
        String token = jwtUtils.generateToken(userDetailsService.loadUserByUsername(authentication.getName()));

        return AuthenticationResponse.of(token);
    }


}
