package org.codewhiskers.vetapp.dto.Vaccination.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaccinationRequestDTO {
    @NotBlank(message = "Aşı adı zorunludur.")
    private String name;

    @NotBlank(message = "Aşı birimi zorunludur (örn: mL, doz).")
    private String vaccinationUnit;
}