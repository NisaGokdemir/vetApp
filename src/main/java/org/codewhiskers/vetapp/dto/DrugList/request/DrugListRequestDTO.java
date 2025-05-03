package org.codewhiskers.vetapp.dto.DrugList.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DrugListRequestDTO {
    @NotNull(message = "İlaç ID zorunludur.")
    private Long drugId;

    @NotBlank(message = "Etken madde zorunludur.")
    private String activeSubstance;

    @NotNull(message = "Kullanım alanı ID zorunludur.")
    private Long usageAreaId;

    @NotNull(message = "Tür ID zorunludur.")
    private Long speciesId;

    @NotBlank(message = "Uygulama yolu zorunludur.")
    private String administrationRoute;
}