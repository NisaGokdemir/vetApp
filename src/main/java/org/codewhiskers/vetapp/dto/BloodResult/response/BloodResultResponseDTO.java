package org.codewhiskers.vetapp.dto.BloodResult.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Clinic.response.ClinicResponseDTO;
import org.codewhiskers.vetapp.dto.Patient.response.PatientResponseDTO;

@Getter
@Setter
public class BloodResultResponseDTO {
    private Long id;
    private String testName;
    private String fileName;
    private String filePath;
    private ClinicResponseDTO clinic;
    private PatientResponseDTO patient;
}
