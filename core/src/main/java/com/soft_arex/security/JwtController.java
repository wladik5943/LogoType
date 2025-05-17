package com.soft_arex.security;


import com.soft_arex.security.contract.JwtRestAPI;
import com.soft_arex.security.model.JwtAuthenticationResponse;
import com.soft_arex.service.security.SignService;
import com.soft_arex.user.model.UserCreateRequest;
import com.soft_arex.user.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class JwtController implements JwtRestAPI {

    private final SignService signService;



    @Override
    public JwtAuthenticationResponse signIn(UserCreateRequest userCreateRequest) {
        return signService.signIn(userCreateRequest);
    }

    @Override
    public JwtAuthenticationResponse signUp(UserCreateRequest userCreateRequest) {
        return signService.signUp(userCreateRequest);
    }

    @Override
    public ResponseEntity<UserResponse> getCurrentUser() {
        return signService.userResponseByEmail();
    }
}
