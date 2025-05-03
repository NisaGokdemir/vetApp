package org.codewhiskers.vetapp.dto.VaccinationBatches.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class VaccinationBatchesRequestDTO {
    @NotNull(message = "Aşılama Envanter ID zorunludur.")
    private Long vaccinationInventoryId;

    @NotNull(message = "Aşılama ID zorunludur.")
    private Long vaccinationId;

    @NotNull(message = "Klinik ID zorunludur.")
    private Long clinicId;

    @NotBlank(message = "Parti numarası zorunludur.")
    private String batchNo;

    @NotNull(message = "Son kullanma tarihi zorunludur.")
    private LocalDate expiryDate;

    @NotNull(message = "Miktar zorunludur.")
    private Integer quantity;

    @NotNull(message = "Alım tarihi zorunludur.")
    private LocalDate receivedDate;
}
