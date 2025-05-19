package com.soft_arex.service.email;

public interface PasswordResetService {
    void sendCode(String email);
    void verifyCode(String email, String code);
    void setNewPassword(String email, String newPassword);
    void sendCodeNewUser(String email,String firstName);
}
