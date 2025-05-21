package com.soft_arex.config;

import com.soft_arex.service.security.JwtService;
import com.soft_arex.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@RequiredArgsConstructor
public class AuthHandshakeInterceptor implements HandshakeInterceptor {

    private final JwtService jwtService;
    private final UserService userService;
    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes) {

        if (request instanceof ServletServerHttpRequest servletRequest) {

            String token = servletRequest.getServletRequest().getParameter("access_token");



                try {
                    String username = jwtService.extractUserName(token);
                    UserDetails userDetails = userService.loadUserByUsername(username);
                    // Валидация токена
                    if (jwtService.isTokenValid(token,userDetails)) {


                        // Сохраняем имя пользователя для использования в WebSocket-сессии
                        attributes.put("username", username);
                        System.out.println("WS handshake: авторизован пользователь " + username);
                    } else {
                        System.out.println("WS handshake: недействительный токен");

                        return false;
                    }
                } catch (Exception e) {
                    System.out.println("WS handshake: ошибка при валидации токена: " + e.getMessage());
                    return false;
                }
        }

        return true;
    }

    @Override
    public void afterHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Exception exception) {
        // ничего не делаем после рукопожатия
    }
}
