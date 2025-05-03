package org.codewhiskers.vetapp.dto.RadiologicImage.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Clinic.response.ClinicResponseDTO;
import org.codewhiskers.vetapp.dto.Patient.response.PatientResponseDTO;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RadiologicImageResponseDTO {
    private Long id;
    private String testName;
    private String fileName;
    private String filePath;
    private LocalDate uploadDate;
    private ClinicResponseDTO clinic;
    private PatientResponseDTO patient;
}
