package com.soft_arex.security.contract;

import com.soft_arex.security.model.JwtAuthenticationResponse;
import com.soft_arex.security.model.RefreshRequest;
import com.soft_arex.user.model.UserCreateRequest;
import com.soft_arex.user.model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("oauth")
public interface JwtRestAPI {

    @PostMapping("/sign-in")
    JwtAuthenticationResponse signIn(@RequestBody UserCreateRequest userCreateRequest);

    @PostMapping("/sign-up")
    JwtAuthenticationResponse signUp(@RequestBody UserCreateRequest userCreateRequest);

    @GetMapping("/me")
    ResponseEntity<UserResponse> getCurrentUser();

    @PostMapping("/refresh-token")
    JwtAuthenticationResponse refreshToken(@RequestBody RefreshRequest request);
}
