package org.codewhiskers.vetapp.dto.Diagnosis.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Patient.response.PatientResponseDTO;
import org.codewhiskers.vetapp.dto.User.response.UserResponseDTO;

@Getter
@Setter
public class DiagnosisResponseDTO {

    private Long id;

    private PatientResponseDTO patient;

    private UserResponseDTO vet;

    private String diagnosis;

    private String treatmentPlan;

}
