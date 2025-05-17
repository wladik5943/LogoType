package com.soft_arex.service.security;

import com.soft_arex.security.model.JwtAuthenticationResponse;
import com.soft_arex.user.model.UserCreateRequest;
import com.soft_arex.user.model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface SignService {
     JwtAuthenticationResponse signUp(UserCreateRequest userCreateRequest);
     JwtAuthenticationResponse signIn(UserCreateRequest userCreateRequest);
     ResponseEntity<UserResponse> userResponseByEmail();
}
