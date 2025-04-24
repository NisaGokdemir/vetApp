package org.codewhiskers.vetapp.jwt;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JwtAuthenticationFilter:
 * - Her HTTP isteği geldiğinde Authorization header'ı kontrol eder.
 * - Bearer token var ise:
 *    1) token'dan kullanıcı adını (sub) okur,
 *    2) henüz context boşsa DB'den UserDetails yükler,
 *    3) token geçerli ise (imza+expiration kontrolü) SecurityContext'e Authentication yerleştirir.
 * - Sonra chain.doFilter ile isteğe devam eder.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // 1) Header kontrolü
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // 2) "Bearer " kısmını at ve token al
        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        // 3) Henüz Authentication yoksa ve token geçerli ise context'i ayarla
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = userDetailsService.loadUserByUsername(username);

            // 4) Token'ın signature ve expiration kontrolü
            if (jwtService.isTokenValid(token, user)) {
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // 5) Context'e set et
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // 6) Filtre zincirine devam
        chain.doFilter(request, response);
    }
}