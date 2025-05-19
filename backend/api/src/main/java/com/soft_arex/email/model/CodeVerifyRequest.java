package com.soft_arex.email.model;

import lombok.Data;

@Data
public class CodeVerifyRequest {
    private String email;
    private String code;
}
