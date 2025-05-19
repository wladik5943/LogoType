package com.soft_arex.controller;

import com.soft_arex.email.contract.PasswordResetRestAPI;
import com.soft_arex.email.model.CodeVerifyRequest;
import com.soft_arex.email.model.PasswordCodeRequest;
import com.soft_arex.email.model.SetNewPasswordRequest;
import com.soft_arex.email.model.VerifyEmailRequest;
import com.soft_arex.entity.User;
import com.soft_arex.service.email.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class PasswordResetController implements PasswordResetRestAPI {

    private final PasswordResetService passwordResetService;

    @Override
    public ResponseEntity<?> sendCode(PasswordCodeRequest req, Authentication auth) {
        String email = (auth != null)
                ? ((User) auth.getPrincipal()).getEmail()
                : req.getEmail();
        passwordResetService.sendCode(email);
        return ResponseEntity.ok("Код отправлен");
    }

    @Override
    public ResponseEntity<?> verify(CodeVerifyRequest req, Authentication auth) {
        String email = (auth != null) ? ((User) auth.getPrincipal()).getEmail() : req.getEmail();
        passwordResetService.verifyCode(email, req.getCode());
        return ResponseEntity.ok("Код подтверждён");
    }

    @Override
    public ResponseEntity<?> setPassword(SetNewPasswordRequest req, Authentication auth) {
        String email = (auth != null) ? ((User) auth.getPrincipal()).getEmail() : req.getEmail();
        passwordResetService.setNewPassword(email, req.getNewPassword());
        return ResponseEntity.ok("Пароль обновлён");
    }

    @Override
    public ResponseEntity<?> verifyEmail(VerifyEmailRequest request) {
        passwordResetService.sendCodeNewUser(request.getEmail(), request.getFirstName());
        return ResponseEntity.ok("Код отправлен");
    }
}
