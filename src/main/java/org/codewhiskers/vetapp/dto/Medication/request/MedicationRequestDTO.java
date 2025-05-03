package org.codewhiskers.vetapp.dto.Medication.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationRequestDTO {

    @NotNull(message = "İlaç ID zorunludur.")
    private Long drugId;

    @NotNull(message = "Klinik ID zorunludur.")
    private Long clinicId;

    @NotNull(message = "Kritik stok seviyesi zorunludur.")
    private Integer stockWarningLevel;
}