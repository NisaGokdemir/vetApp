package org.codewhiskers.vetapp.dto.FollowUp.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowUpRequestDTO {
    private String startDate;
    private String endDate;
    private String frequency;
    private String nextFollowUpDate;
    private String notes;
    private Long diagnosisId;
}
