package org.codewhiskers.vetapp.dto.PatientAllergy.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientAllergyRequestDTO {
    private Long patientId;
    private String allergy;
    private String severity;
    private String note;
}
