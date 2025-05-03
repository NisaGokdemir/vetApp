package org.codewhiskers.vetapp.dto.Clinic.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicRequestDTO {
    @NotBlank(message = "Clinic name is required")
    private String name;
    @NotBlank(message = "Clinic address is required")
    private String address;

    private String phoneNumber;
    private String email;
}
