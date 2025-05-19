package com.soft_arex.service.email;

public interface EmailService {
    void sendCode(String to, String name, String code);
}
