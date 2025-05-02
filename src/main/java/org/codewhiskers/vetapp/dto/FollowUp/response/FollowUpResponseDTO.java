package org.codewhiskers.vetapp.dto.FollowUp.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Diagnosis.response.DiagnosisResponseDTO;

import java.time.LocalDate;

@Getter
@Setter
public class FollowUpResponseDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String frequency;
    private LocalDate nextFollowUpDate;
    private String notes;
    private DiagnosisResponseDTO diagnosis;
}
