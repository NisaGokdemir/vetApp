package org.codewhiskers.vetapp.dto.PatientAllergy.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Patient.response.PatientResponseDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientAllergyResponseDTO {
    private Long id;
    private PatientResponseDTO patient;
    private String allergy;
    private String severity;
    private String note;
}
