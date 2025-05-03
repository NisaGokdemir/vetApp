package org.codewhiskers.vetapp.dto.User.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class
UserRequestDTO {

    @NotBlank(message = "Kullanıcı adı boş olamaz.")
    private String username;

    @NotBlank(message = "Şifre boş olamaz.")
    private String password;

}
