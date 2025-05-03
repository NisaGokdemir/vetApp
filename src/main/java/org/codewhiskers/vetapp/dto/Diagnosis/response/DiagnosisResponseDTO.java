package org.codewhiskers.vetapp.dto.Diagnosis.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Clinic.response.ClinicResponseDTO;
import org.codewhiskers.vetapp.dto.Disease.response.DiseaseResponseDTO;
import org.codewhiskers.vetapp.dto.Patient.response.PatientResponseDTO;
import org.codewhiskers.vetapp.dto.Veterinarian.response.VeterinarianResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiagnosisResponseDTO {

    private Long id;

    private PatientResponseDTO patient;

    private ClinicResponseDTO clinic;

    private VeterinarianResponseDTO veterinarian;
    
    private DiseaseResponseDTO disease;

    private String diagnosis;

    private String treatmentPlan;

    private String symptoms;

    private String notes;
    
    private LocalDate diagnosisDate;

    private LocalDateTime nextFollowUpDate;
}
