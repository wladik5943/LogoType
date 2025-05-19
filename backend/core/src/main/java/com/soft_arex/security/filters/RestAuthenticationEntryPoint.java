package com.soft_arex.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soft_arex.exeption.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ExceptionResponse responseError = createResponseError(authException.getMessage());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, responseError);
        out.flush();
    }

    private ExceptionResponse createResponseError(String message) {
        ExceptionResponse errorNoClient = new ExceptionResponse();
        errorNoClient.setCode(401);
        errorNoClient.setStatus(HttpStatus.UNAUTHORIZED);
        errorNoClient.setStatusInt(401);
        errorNoClient.setMessage(message);
        return errorNoClient;
    }

}