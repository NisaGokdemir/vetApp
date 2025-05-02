package org.codewhiskers.vetapp.dto.Diagnosis.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class DiagnosisRequestDTO {
    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Vet ID is required")
    private Long vetId;

    @NotBlank(message = "Diagnosis is required")
    @Size(min = 5, max = 255, message = "Diagnosis must be between 5 and 255 characters")
    private String diagnosis;

    @NotBlank(message = "Clinic is required")
    private Long clinicId;

    @NotBlank(message = "Treatment plan is required")
    private String treatmentPlan;

    private String symptoms;

    private String notes;

    private LocalDate nextFollowUpDate;
}
