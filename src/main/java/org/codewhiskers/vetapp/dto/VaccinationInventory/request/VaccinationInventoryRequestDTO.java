package org.codewhiskers.vetapp.dto.VaccinationInventory.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaccinationInventoryRequestDTO {
    @NotNull(message = "Aşılama ID zorunludur.")
    private Long vaccinationId;

    @NotNull(message = "Klinik ID zorunludur.")
    private Long clinicId;

    @NotNull(message = "Stok uyarı seviyesi zorunludur.")
    private Integer stockWarningLevel;
}