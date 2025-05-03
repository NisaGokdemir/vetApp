package org.codewhiskers.vetapp.dto.MedicationBatch.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Clinic.response.ClinicResponseDTO;
import org.codewhiskers.vetapp.dto.Drug.response.DrugResponseDTO;
import org.codewhiskers.vetapp.dto.Medication.response.MedicationResponseDTO;

import java.time.LocalDateTime;

@Getter
@Setter
public class MedicationBatchResponseDTO {

    private Long id;
    private MedicationResponseDTO medication;
    private DrugResponseDTO drug;
    private ClinicResponseDTO clinic;
    private Integer quantity;
    private LocalDateTime expiryDate;
    private LocalDateTime receivedDate;

}