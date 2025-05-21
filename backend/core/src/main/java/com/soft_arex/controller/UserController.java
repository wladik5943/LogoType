package com.soft_arex.controller;

import com.soft_arex.service.user.UserService;
import com.soft_arex.user.contract.UserRestAPI;
import com.soft_arex.user.model.UserCreateRequest;
import com.soft_arex.user.model.UserResponse;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class UserController implements UserRestAPI {

    private final UserService userService;

    @Override
    public UserResponse  editProfile(UserCreateRequest request) {
        return userService.editUser(request);
    }
}
