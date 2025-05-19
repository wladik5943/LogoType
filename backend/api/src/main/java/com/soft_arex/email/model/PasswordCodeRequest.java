package com.soft_arex.email.model;

import lombok.Data;

@Data
public class PasswordCodeRequest {
    private String email; // необязателен для авторизованных
}
