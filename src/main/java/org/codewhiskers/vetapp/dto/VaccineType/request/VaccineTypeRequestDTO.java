package org.codewhiskers.vetapp.dto.VaccineType.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaccineTypeRequestDTO {
    @NotBlank(message = "Aşı tipi adı zorunludur.")
    private String name;
}