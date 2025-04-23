package org.codewhiskers.vetapp.config;

import lombok.RequiredArgsConstructor;
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
    private final CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers("/api/specializations/**").hasRole("ADMIN")
                        .requestMatchers("/api/species/**").hasAnyRole("ADMIN","VETERINARIAN")
                        .requestMatchers("/api/bloodTypes/**").hasAnyRole("ADMIN","VETERINARIAN")
                        .requestMatchers("/api/breeds/**").hasAnyRole("ADMIN","VETERINARIAN")
                        .requestMatchers("/api/diagnosis/**").hasAnyRole("ADMIN","VETERINARIAN")
                        .requestMatchers("/api/prescriptions/**").hasAnyRole("ADMIN","VETERINARIAN")
                        .requestMatchers("/api/medication/**").hasAnyRole("ADMIN","VETERINARIAN")
                        .requestMatchers("/api/medication/**").hasAnyRole("ADMIN","VETERINARIAN")
                        .requestMatchers("/api/prescriptions/**").hasAnyRole("ADMIN","VETERINARIAN")
                        .requestMatchers("/api/medication-batch/**").hasAnyRole("ADMIN","VETERINARIAN")
                        .requestMatchers("/api/owners/**").hasAnyRole("ADMIN","VETERINARIAN", "RECEPTIONIST")
                        .requestMatchers("/api/patients/**").hasAnyRole("ADMIN","VETERINARIAN", "RECEPTIONIST")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
