package com.soft_arex.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.management.ConstructorParameters;

@Data
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String accessToken;
    private String refreshToken;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
