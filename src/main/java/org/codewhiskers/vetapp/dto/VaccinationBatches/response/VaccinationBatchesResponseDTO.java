package org.codewhiskers.vetapp.dto.VaccinationBatches.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Clinic.response.ClinicResponseDTO;
import org.codewhiskers.vetapp.dto.Vaccination.response.VaccinationResponseDTO;
import org.codewhiskers.vetapp.dto.VaccinationInventory.response.VaccinationInventoryResponseDTO;
import org.codewhiskers.vetapp.entity.VaccinationInventory;

import java.time.LocalDate;

@Getter
@Setter
public class VaccinationBatchesResponseDTO {
    private Long id;
    private VaccinationInventoryResponseDTO vaccinationInventory;
    private VaccinationResponseDTO vaccination;
    private ClinicResponseDTO clinic;
    private String batchNo;
    private LocalDate expiryDate;
    private Integer quantity;
    private LocalDate receivedDate;
}