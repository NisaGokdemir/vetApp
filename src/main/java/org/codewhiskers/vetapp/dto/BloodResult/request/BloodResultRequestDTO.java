package org.codewhiskers.vetapp.dto.BloodResult.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BloodResultRequestDTO {
    private String testName;
    private String fileName;
    private String filePath;
    private Long clinicId;
    private Long patientId;
}
