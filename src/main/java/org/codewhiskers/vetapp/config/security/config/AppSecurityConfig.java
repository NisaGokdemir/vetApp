package org.codewhiskers.vetapp.config.security.config;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.config.security.handler.CustomAccessDeniedHandler;
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
                        // Auth ve Swagger endpointleri herkese açık
                        .requestMatchers("/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        
                        // Klinik ve uzmanlık listeleri herkese açık
                        .requestMatchers("/api/clinics/all", "/api/specializations/all").permitAll()
                        
                        // Sadece Admin yetkisi gerektiren endpointler
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers("/api/clinics/**").hasRole("ADMIN")
                        .requestMatchers("/api/specializations/**").hasRole("ADMIN")
                        .requestMatchers("/api/species/**").hasRole("ADMIN")
                        .requestMatchers("/api/bloodTypes/**").hasRole("ADMIN")
                        .requestMatchers("/api/breeds/**").hasRole("ADMIN")
                        .requestMatchers("/api/drugs/**").hasRole("ADMIN")
                        .requestMatchers("/api/drug-lists/**").hasRole("ADMIN")
                        .requestMatchers("/api/drug-protocols/**").hasRole("ADMIN")
                        .requestMatchers("/api/vaccinations/**").hasRole("ADMIN")
                        .requestMatchers("/api/vaccination-batches/**").hasRole("ADMIN")
                        .requestMatchers("/api/vaccination-inventory/**").hasRole("ADMIN")
                        
                        // Admin ve Veteriner yetkisi gerektiren endpointler
                        .requestMatchers("/api/diagnosis/**").hasAnyRole("ADMIN", "VETERINARIAN")
                        .requestMatchers("/api/owners/**").hasAnyRole("ADMIN", "VETERINARIAN")
                        .requestMatchers("/api/patients/**").hasAnyRole("ADMIN", "VETERINARIAN")
                        .requestMatchers("/api/families/**").hasAnyRole("ADMIN", "VETERINARIAN")
                        .requestMatchers("/api/prescriptions/**").hasAnyRole("ADMIN", "VETERINARIAN")
                        .requestMatchers("/api/prescription-items/**").hasAnyRole("ADMIN", "VETERINARIAN")
                        .requestMatchers("/api/medication/**").hasAnyRole("ADMIN", "VETERINARIAN")
                        .requestMatchers("/api/medication-batch/**").hasAnyRole("ADMIN", "VETERINARIAN")

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
