package com.soft_arex.service.email.impl;

import com.soft_arex.entity.User;
import com.soft_arex.exeption.UserException;
import com.soft_arex.repository.UserRepository;
import com.soft_arex.service.email.EmailService;
import com.soft_arex.service.email.PasswordResetService;
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

    private final Map<String, String> codeStorage = new ConcurrentHashMap<>();
    private final Set<String> confirmedEmails = ConcurrentHashMap.newKeySet();

    public void sendCode(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new UserException("Пользователь не найден", HttpStatus.NOT_FOUND);

        String code = String.valueOf(new Random().nextInt(900000) + 100000);
        codeStorage.put(email, code);

        emailService.sendCode(email, user.getFirstName(), code);
    }

    public void verifyCode(String email, String code) {
        if (!code.equals(codeStorage.get(email))) {
            throw new UserException("Неверный код", HttpStatus.UNAUTHORIZED);
        }
        codeStorage.remove(email);
        confirmedEmails.add(email); // Разрешаем смену пароля
    }

    public void setNewPassword(String email, String newPassword) {
        if (!confirmedEmails.contains(email)) {
            throw new UserException("Подтверждение кода обязательно", HttpStatus.FORBIDDEN);
        }
        confirmedEmails.remove(email);

        User user = userRepository.findByEmail(email);
        if (user == null) throw new UserException("Пользователь не найден", HttpStatus.NOT_FOUND);

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}