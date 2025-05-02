package org.codewhiskers.vetapp.dto.RadiologicImage.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Clinic.response.ClinicResponseDTO;
import org.codewhiskers.vetapp.dto.Patient.response.PatientResponseDTO;

import java.time.LocalDate;

@Getter
@Setter
public class RadiologicImageResponseDTO {
    private Long id;
    private String imageName;
    private LocalDate uploadDate;
    private String filePath;
    private ClinicResponseDTO clinic;
    private PatientResponseDTO patient;
}
