package org.codewhiskers.vetapp.dto.Diagnosis.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiagnosisRequestDTO {
    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Veterinarian ID is required")
    private Long veterinarianId;

    @NotNull(message = "Clinic ID is required")
    private Long clinicId;
    
    @NotNull(message = "Disease ID is required")
    private Long diseaseId;

    @NotBlank(message = "Diagnosis is required")
    @Size(min = 5, max = 255, message = "Diagnosis must be between 5 and 255 characters")
    private String diagnosis;

    @NotBlank(message = "Treatment plan is required")
    private String treatmentPlan;

    private String symptoms;

    private String notes;

    private LocalDateTime nextFollowUpDate;
}
