package com.soft_arex.service.user;

import com.soft_arex.entity.User;
import com.soft_arex.user.model.UserCreateRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


public interface UserService {
    public User register(UserCreateRequest userCreateRequest);
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
