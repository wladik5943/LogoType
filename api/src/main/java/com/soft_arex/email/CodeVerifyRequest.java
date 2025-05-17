package com.soft_arex.email;

import lombok.Data;

@Data
public class CodeVerifyRequest {
    private String email;
    private String code;
}
