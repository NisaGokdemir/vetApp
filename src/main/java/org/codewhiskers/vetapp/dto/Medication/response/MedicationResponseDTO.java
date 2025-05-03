package org.codewhiskers.vetapp.dto.Medication.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Clinic.response.ClinicResponseDTO;
import org.codewhiskers.vetapp.dto.Drug.response.DrugResponseDTO;

@Getter
@Setter
public class MedicationResponseDTO {
    private Long id;
    private DrugResponseDTO drug;
    private ClinicResponseDTO clinic;
    private Integer totalQuantity;
    private Integer stockWarningLevel;
}