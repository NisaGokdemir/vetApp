package org.codewhiskers.vetapp.dto.RadiologicImage.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RadiologicImageRequestDTO {
    private String imageName;
    private String filePath;
    private Long clinicId;
    private Long patientId;
}
