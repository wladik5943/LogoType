package com.soft_arex.service.security.impl;

import com.soft_arex.entity.User;
import com.soft_arex.exeption.UserException;
import com.soft_arex.mapper.UserMapper;
import com.soft_arex.repository.UserRepository;
import com.soft_arex.security.model.JwtAuthenticationResponse;
import com.soft_arex.service.security.SignService;
import com.soft_arex.service.user.UserService;
import com.soft_arex.user.model.UserCreateRequest;
import com.soft_arex.user.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SignServiceImpl implements SignService {

    private final UserService userService;
    private final JwtServiceImpl jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<UserResponse> userResponseByEmail() {
        User userByToken = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(userByToken.getId());
        return ResponseEntity.ok(userMapper.toResponse(user.get()));
    }



    @Override
    public JwtAuthenticationResponse signUp(UserCreateRequest userCreateRequest) {
        var register = userService.register(userCreateRequest);
        return new JwtAuthenticationResponse(jwtService.generateToken((UserDetails) register));
    }

    @Override
    public JwtAuthenticationResponse signIn(UserCreateRequest userCreateRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userCreateRequest.getEmail(),
                            userCreateRequest.getPassword()
                    )
            );

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            User user =  (User) authentication.getPrincipal(); // кастим, если ты используешь CustomUserDetails

            String jwt = jwtService.generateToken(user);
            return new JwtAuthenticationResponse(jwt);

        } catch (AuthenticationException e) {
            throw new UserException("неверный логин или пароль", HttpStatus.UNAUTHORIZED);
        }
    }
}
