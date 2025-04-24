package org.codewhiskers.vetapp.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * AccessDeniedHandler:
 * - Kimlik doğrulaması olmuş ama yetki eksikliğinden (403) dolayı erişime izin verilmeyen durumlarda
 *   bu kod çalışır.
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException exception) throws IOException {

        // 403 Forbidden
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        ErrorMessage err = new ErrorMessage(
                MessageType.UNAUTHORIZED_ACCESS,
                "Erişim yetkiniz bulunmamaktadır"
        );
        response.getWriter().write(objectMapper.writeValueAsString(err));
    }
}