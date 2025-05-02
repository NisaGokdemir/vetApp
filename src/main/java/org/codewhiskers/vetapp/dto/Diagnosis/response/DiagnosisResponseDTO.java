package org.codewhiskers.vetapp.dto.Diagnosis.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Clinic.response.ClinicResponseDTO;
import org.codewhiskers.vetapp.dto.Patient.response.PatientResponseDTO;
import org.codewhiskers.vetapp.dto.User.response.UserResponseDTO;

@Getter
@Setter
public class DiagnosisResponseDTO {

    private Long id;

    private PatientResponseDTO patient;

    private ClinicResponseDTO clinic;

    private UserResponseDTO vet;

    private String diagnosis;

    private String treatmentPlan;

    private String symptoms;

    private String notes;

}
