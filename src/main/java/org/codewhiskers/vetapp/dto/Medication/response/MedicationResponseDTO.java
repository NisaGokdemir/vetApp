package org.codewhiskers.vetapp.dto.Medication.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MedicationResponseDTO {
    private Long id;
    private String name;
    private String unit;
    private Integer stockWarningLevel;
}
