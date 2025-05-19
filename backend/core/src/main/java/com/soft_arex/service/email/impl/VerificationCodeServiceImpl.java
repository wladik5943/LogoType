package com.soft_arex.service.email.impl;

import com.soft_arex.service.email.VerificationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private static final String CONFIRMED_PREFIX = "confirmed:";

    private final StringRedisTemplate redisTemplate;

    private static final String PREFIX = "verify_code:";

    @Override
    public void saveCode(String email, String code, long minutesToExpire) {
        redisTemplate.opsForValue().set(PREFIX + email, code, minutesToExpire, TimeUnit.MINUTES);
    }

    @Override
    public String getCode(String email) {
        return redisTemplate.opsForValue().get(PREFIX + email);
    }

    @Override
    public void deleteCode(String email) {
        redisTemplate.delete(PREFIX + email);
    }

    @Override
    public boolean checkCode(String email, String code) {
        String storedCode = getCode(email);
        return storedCode != null && storedCode.equals(code);
    }

    @Override
    public void markAsConfirmed(String email) {
        redisTemplate.opsForValue().set(CONFIRMED_PREFIX + email, "true", 15, TimeUnit.MINUTES);
    }

    @Override
    public boolean isConfirmed(String email) {
        return "true".equals(redisTemplate.opsForValue().get(CONFIRMED_PREFIX + email));
    }

    @Override
    public void removeConfirmation(String email) {
        redisTemplate.delete(CONFIRMED_PREFIX + email);
    }
}
