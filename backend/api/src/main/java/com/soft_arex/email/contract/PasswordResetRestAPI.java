package com.soft_arex.email.contract;

import com.soft_arex.email.model.CodeVerifyRequest;
import com.soft_arex.email.model.PasswordCodeRequest;
import com.soft_arex.email.model.SetNewPasswordRequest;
import com.soft_arex.email.model.VerifyEmailRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/password")
public interface PasswordResetRestAPI {
    @PostMapping("/send-code")
    ResponseEntity<?> sendCode(@RequestBody PasswordCodeRequest req, Authentication auth);

    @PostMapping("/verify-code")
    ResponseEntity<?> verify(@RequestBody CodeVerifyRequest req, Authentication auth);

    @PostMapping("/set-password")
    ResponseEntity<?> setPassword(@RequestBody SetNewPasswordRequest req, Authentication auth);

    @PostMapping("/verify-email")
    ResponseEntity<?> verifyEmail(@RequestBody VerifyEmailRequest request);
}
