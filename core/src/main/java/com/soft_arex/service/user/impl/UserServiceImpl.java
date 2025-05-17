package com.soft_arex.service.user.impl;

import com.soft_arex.entity.User;
import com.soft_arex.enums.UserRole;
import com.soft_arex.exeption.UserException;
import com.soft_arex.mapper.UserMapper;
import com.soft_arex.repository.UserRepository;
import com.soft_arex.service.user.UserService;
import com.soft_arex.user.model.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder standartPasswordEncoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;






    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public User register(UserCreateRequest userCreateRequest) {
        var user = userMapper.toEntity(userCreateRequest);
        String encode = standartPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        user.setRole(UserRole.USER);
        if(userRepository.findByEmail(user.getEmail()) == null)
            return userRepository.save(user);
        else
            throw new UserException("данный email уже занят", HttpStatus.CONFLICT);
    }


}
