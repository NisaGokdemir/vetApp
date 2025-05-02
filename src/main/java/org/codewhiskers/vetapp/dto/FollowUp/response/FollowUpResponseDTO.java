package org.codewhiskers.vetapp.dto.FollowUp.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Diagnosis.response.DiagnosisResponseDTO;

@Getter
@Setter
public class FollowUpResponseDTO {
    private Long id;
    private String startDate;
    private String endDate;
    private String frequency;
    private String nextFollowUpDate;
    private String notes;
    private DiagnosisResponseDTO diagnosis;
}
