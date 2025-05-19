package com.soft_arex.user.contract;

import com.soft_arex.user.model.UserCreateRequest;
import com.soft_arex.user.model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("user")
public interface UserRestAPI {

    @PostMapping("/edit-profile")
    UserResponse editProfile(@RequestBody UserCreateRequest request);
}
