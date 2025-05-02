package org.codewhiskers.vetapp.dto.PatientAllergy.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Patient.response.PatientResponseDTO;

@Getter
@Setter
public class PatientAllergyResponseDTO {
    private Long id;
    private PatientResponseDTO patient;
    private String allergy;
    private String severity;
    private String note;

}
