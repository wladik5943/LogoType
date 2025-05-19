package com.soft_arex.service.email.impl;

import com.soft_arex.entity.User;
import com.soft_arex.exeption.UserException;
import com.soft_arex.repository.UserRepository;
import com.soft_arex.service.email.EmailService;
import com.soft_arex.service.email.PasswordResetService;
import com.soft_arex.service.email.VerificationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final EmailService emailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final VerificationCodeService verificationCodeService;


    public void sendCode(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new UserException("Пользователь не найден", HttpStatus.NOT_FOUND);

        String code = String.valueOf(new Random().nextInt(900000) + 100000);
        verificationCodeService.saveCode(email, code, 5);

        emailService.sendCode(email, user.getFirstName(), code);
    }

    public void verifyCode(String email, String code) {
        if (!verificationCodeService.checkCode(email, code)) {
            throw new UserException("Неверный код", HttpStatus.UNAUTHORIZED);
        }
        verificationCodeService.deleteCode(email);

        if (userRepository.findByEmail(email) != null) {
            verificationCodeService.markAsConfirmed(email);
        }
    }

    public void setNewPassword(String email, String newPassword) {
        if (!verificationCodeService.isConfirmed(email)) {
            throw new UserException("Подтверждение кода обязательно", HttpStatus.FORBIDDEN);
        }
        verificationCodeService.removeConfirmation(email);

        User user = userRepository.findByEmail(email);
        if (user == null) throw new UserException("Пользователь не найден", HttpStatus.NOT_FOUND);

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public void sendCodeNewUser(String email, String firstName) {

        if (userRepository.findByEmail(email) != null)
            throw new UserException("пользователь с такой почтой уже существует", HttpStatus.CONFLICT);

        String code = String.valueOf(new Random().nextInt(900000) + 100000);
        verificationCodeService.saveCode(email, code, 5);

        emailService.sendCode(email, firstName, code);
    }
}