package org.codewhiskers.vetapp.dto.FollowUp.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FollowUpRequestDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private String frequency;
    private LocalDate nextFollowUpDate;
    private String notes;
    private Long diagnosisId;
}
