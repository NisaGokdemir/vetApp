package org.codewhiskers.vetapp.dto.User.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Klinik ID boş olamaz.")
    @JsonProperty("clinic_id")
    private Long clinicId;

    @NotNull(message = "Uzmanlık ID boş olamaz.")
    @JsonProperty("specialization_id")
    private Long specializationId;

}
