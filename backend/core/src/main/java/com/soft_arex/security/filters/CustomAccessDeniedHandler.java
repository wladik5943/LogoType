package com.soft_arex.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soft_arex.exeption.ExceptionResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ExceptionResponse responseError = createResponseError(accessDeniedException.getMessage());
        OutputStream out = response.getOutputStream();
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, responseError);
        out.flush();
    }

    private ExceptionResponse createResponseError(String message) {
        ExceptionResponse errorNoClient = new ExceptionResponse();
        errorNoClient.setCode(403);
        errorNoClient.setStatus(HttpStatus.FORBIDDEN);
        errorNoClient.setMessage(message);
        return errorNoClient;
    }
}