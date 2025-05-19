package com.soft_arex.email.model;

import lombok.Data;

@Data
public class VerifyEmailRequest {
    private String email;
    private String firstName;
}
