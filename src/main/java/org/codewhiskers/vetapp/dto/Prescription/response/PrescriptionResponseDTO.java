package org.codewhiskers.vetapp.dto.Prescription.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Diagnosis.response.DiagnosisResponseDTO;

import java.time.LocalDate;

@Getter
@Setter
public class PrescriptionResponseDTO {
    private Long id;
    private String notes;
    private DiagnosisResponseDTO diagnosis;
    private LocalDate createdAt;
}
