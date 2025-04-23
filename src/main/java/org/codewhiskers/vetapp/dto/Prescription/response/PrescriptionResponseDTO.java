package org.codewhiskers.vetapp.dto.Prescription.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Diagnosis.response.DiagnosisResponseDTO;

@Getter
@Setter
public class PrescriptionResponseDTO {
    private String notes;
    private DiagnosisResponseDTO diagnosis;
}
