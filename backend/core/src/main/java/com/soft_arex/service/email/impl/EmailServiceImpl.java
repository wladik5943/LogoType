package com.soft_arex.service.email.impl;

import com.soft_arex.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    public void sendCode(String to, String name, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Код подтверждения");
        message.setText("Привет, " + name + "!\n\nКод подтверждения: " + code + "\n Если это не вы — просто проигнорируйте.");

        mailSender.send(message);
    }
}
