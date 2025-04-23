package org.codewhiskers.vetapp.dto.Prescription.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrescriptionRequestDTO {

    @NotNull(message = "Diagnosis ID cannot be null")
    private Long diagnosisId;

    @NotNull(message = "Notes cannot be null")
    private String notes;
}
