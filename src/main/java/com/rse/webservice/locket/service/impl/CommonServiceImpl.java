package com.rse.webservice.locket.service.impl;

import com.rse.webservice.locket.exception.ApiRequestException;
import com.rse.webservice.locket.model.User;
import com.rse.webservice.locket.repository.AccountRepository;
import com.rse.webservice.locket.repository.UserRepository;
import com.rse.webservice.locket.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * Service implementation providing common functionalities related to user authentication.
 *
 * @author MENES
 * @version 0.0.1
 * @since 12/30/2023
 */
@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {
    private static final Long NO_LOGIN_USER_ID = -1L;
    private static final Long NO_LOGIN_ACCOUNT_ID = -2L;


    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    /**
     * Retrieves the UserDetails of the currently authenticated user from the SecurityContextHolder.
     *
     * @return The UserDetails of the authenticated user, or {@code null} if no user is authenticated.
     * @see SecurityContextHolder
     * @see Authentication
     * @see UserDetails
     */
    public UserDetails userDetails() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(UserDetails.class::isInstance)
                .map(UserDetails.class::cast)
                .orElse(null);
    }

    /**
     * Retrieves the ID of the currently authenticated user.
     *
     * @return The ID of the authenticated user, or {@code null} if no user is authenticated or the user details
     * do not contain an ID.
     */
    public Long getLoginId() {
        return Optional.ofNullable(userDetails())
                .filter(userDetails -> userDetails instanceof User)
                .map(userDetails -> ((User) userDetails).getId())
                .orElse(NO_LOGIN_USER_ID);
    }

    @Override
    public Long getCurrentAccountId() {
        return accountRepository
                .findByUserId(getLoginId()).orElseThrow(() -> new ApiRequestException("Account not found"))
                .getId();
    }

    /**
     * Retrieves the username of the currently authenticated user.
     *
     * @return The username of the authenticated user, or {@code null} if no user is authenticated or the user details
     * do not contain a username.
     */
    @Override
    public String getLoginUsername() {
        return Optional.ofNullable(userDetails())
                .filter(userDetails -> userDetails instanceof User)
                .map(UserDetails::getUsername)
                .orElse(null);
    }

    /**
     * Checks if a user with the specified ID exists in the repository.
     *
     * @param userId The ID of the user to check.
     * @return {@code true} if a user with the specified ID exists; {@code false} otherwise.
     */
    @Override
    public Boolean existsUser(Long userId) {
        return userRepository.findById(userId).isPresent();
    }

    @Override
    public Boolean isNoLoginAccount(Long accountId) {
        return Objects.equals(accountId, NO_LOGIN_ACCOUNT_ID);
    }

    @Override
    public Boolean isNoLogin() {
        return isNoLoginAccount(getCurrentAccountId());
    }
}
