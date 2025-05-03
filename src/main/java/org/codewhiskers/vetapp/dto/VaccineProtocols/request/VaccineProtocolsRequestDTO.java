package org.codewhiskers.vetapp.dto.VaccineProtocols.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaccineProtocolsRequestDTO {
    @NotNull(message = "Aşılama ID zorunludur.")
    private Long vaccinationId;

    @NotBlank(message = "Marka zorunludur.")
    private String brand;

    @NotNull(message = "Tür ID zorunludur.")
    private Long speciesId;

    @NotNull(message = "İlk doz haftası zorunludur.")
    private Integer firstDoseWeek;

    private Integer boosterInterval;

    @NotNull(message = "Kilo bazlı olup olmadığı bilgisi zorunludur.")
    private Boolean isWeightBased;

    private Double dosePerKg;

    private Double maxSingleDose;
}