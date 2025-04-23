package org.codewhiskers.vetapp.jwt;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    @NotEmpty(message = "Kullanıcı adı boş olamaz")
    private String username;

    @NotEmpty(message = "Şifre boş olamaz")
    private String password;
}
