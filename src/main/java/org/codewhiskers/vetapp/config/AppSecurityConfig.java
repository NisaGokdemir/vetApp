package org.codewhiskers.vetapp.config;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.jwt.AuthEntryPoint;
import org.codewhiskers.vetapp.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class AppSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final AuthEntryPoint authEntryPoint;               // 401 hatalarını handle eder
    private final CustomAccessDeniedHandler accessDeniedHandler; // 403 hatalarını handle eder

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 1) CSRF korumasını devre dışı bırakıyoruz; JWT ile stateless API'lerde genelde ihtiyaç olmaz.
                .csrf(AbstractHttpConfigurer::disable)

                // 2) Session yönetimini STATELESS yapıyoruz; her istek başına kimlik doğrulaması yapılacak.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3) URL bazlı yetkilendirme:
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers("/api/specializations/all").permitAll()
                        .requestMatchers("/api/specializations/**").hasRole("ADMIN")
                        .requestMatchers("/api/species/**").hasAnyRole("ADMIN","VETERINARIAN")
                        .requestMatchers("/api/bloodTypes/**").hasAnyRole("ADMIN","VETERINARIAN")
                        .requestMatchers("/api/breeds/**").hasAnyRole("ADMIN","VETERINARIAN")
                        .requestMatchers("/api/diagnosis/**").hasAnyRole("ADMIN","VETERINARIAN")
                        .requestMatchers("/api/prescriptions/**").hasAnyRole("ADMIN","VETERINARIAN")
                        .requestMatchers("/api/prescription-items/**").hasAnyRole("ADMIN","VETERINARIAN")
                        .requestMatchers("/api/medication/**").hasAnyRole("ADMIN","VETERINARIAN")
                        .requestMatchers("/api/medication-batch/**").hasAnyRole("ADMIN","VETERINARIAN")
                        .requestMatchers("/api/owners/**").hasAnyRole("ADMIN","VETERINARIAN", "RECEPTIONIST")
                        .requestMatchers("/api/patients/**").hasAnyRole("ADMIN","VETERINARIAN", "RECEPTIONIST")

                        // Diğer tüm yollar için doğrulanmış kullanıcı gerekiyor
                        .anyRequest().authenticated()
                )

                // 4) Exception handling:
                .exceptionHandling(ex -> ex
                        // AuthenticationException (401) → AuthEntryPoint
                        .authenticationEntryPoint(authEntryPoint)
                        // AccessDeniedException (403) → CustomAccessDeniedHandler
                        .accessDeniedHandler(accessDeniedHandler)
                )

                // 5) Bizim DaoAuthenticationProvider'ı kullan
                .authenticationProvider(authenticationProvider)

                // 6) JWT doğrulama filtresi, UsernamePasswordAuthenticationFilter'dan önce çalışsın
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }
}
