package org.codewhiskers.vetapp.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * AuthenticationEntryPoint:
 * - Kullanıcı henüz kimlik doğrulaması yapmadan korumalı bir endpoint'e erişmeye çalıştığında
 *   (ör. token eksik veya geçersiz) burası devreye girer ve 401 döner.
 */
@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {

        // 401 Unauthorized
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        // Kendi hata modelinize uygun JSON üretimi
        ErrorMessage err = new ErrorMessage(
                MessageType.UNAUTHORIZED_ACCESS,
                "URI: " + request.getRequestURI()
        );
        response.getWriter().write(objectMapper.writeValueAsString(err));
    }
}