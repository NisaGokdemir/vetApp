package org.codewhiskers.vetapp.dto.Medication.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MedicationRequestDTO {
    @NotBlank(message = "Medication name is required")
    private String name;

    @NotBlank(message = "Unit is required")
    private String unit;

    @NotNull(message = "Stock warning level is required")
    private Integer stockWarningLevel;
}
