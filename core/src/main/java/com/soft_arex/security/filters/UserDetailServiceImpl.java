package com.soft_arex.security.filters;

import com.soft_arex.entity.User;
import com.soft_arex.exeption.UserException;
import com.soft_arex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String email)  {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserException("неверный логин", HttpStatus.BAD_REQUEST);
        }
        return user;
    }
}