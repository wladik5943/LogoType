package com.soft_arex.service.security;

import com.soft_arex.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    public String generateToken(UserDetails userDetails) ;
    public String extractUserName(String token);
    public boolean isTokenValid(String token, UserDetails userDetails);
    User getUserByToken();
}
