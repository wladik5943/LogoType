package com.soft_arex.service.email;

public interface VerificationCodeService {
    void saveCode(String email, String code, long minutesToExpire);
    String getCode(String email);
    void deleteCode(String email);
    boolean checkCode(String email, String code);
    void markAsConfirmed(String email);
    boolean isConfirmed(String email);
    void removeConfirmation(String email);
}
