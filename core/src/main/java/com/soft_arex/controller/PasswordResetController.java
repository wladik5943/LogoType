package com.soft_arex.controller;

import com.soft_arex.email.CodeVerifyRequest;
import com.soft_arex.email.PasswordCodeRequest;
import com.soft_arex.email.SetNewPasswordRequest;
import com.soft_arex.entity.User;
import com.soft_arex.service.email.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/password")
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    @PostMapping("/send-code")
    public ResponseEntity<?> sendCode(@RequestBody PasswordCodeRequest req, Authentication auth) {
        String email = (auth != null)
                ? ((User) auth.getPrincipal()).getEmail()
                : req.getEmail();
        passwordResetService.sendCode(email);
        return ResponseEntity.ok("Код отправлен");
    }

    @PostMapping("/verify-code")
    public ResponseEntity<?> verify(@RequestBody CodeVerifyRequest req, Authentication auth) {
        String email = (auth != null) ? ((User) auth.getPrincipal()).getEmail() : req.getEmail();
        passwordResetService.verifyCode(email, req.getCode());
        return ResponseEntity.ok("Код подтверждён");
    }

    @PostMapping("/set-password")
    public ResponseEntity<?> setPassword(@RequestBody SetNewPasswordRequest req, Authentication auth) {
        String email = (auth != null) ? ((User) auth.getPrincipal()).getEmail() : req.getEmail();
        passwordResetService.setNewPassword(email, req.getNewPassword());
        return ResponseEntity.ok("Пароль обновлён");
    }
}
