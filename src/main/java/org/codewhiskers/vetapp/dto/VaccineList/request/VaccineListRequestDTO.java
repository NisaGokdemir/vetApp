package org.codewhiskers.vetapp.dto.VaccineList.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaccineListRequestDTO {
    @NotNull(message = "Aşı ID'si zorunludur.")
    private Long vaccineId;

    @NotBlank(message = "Hastalık hedefi zorunludur.")
    private String diseaseTarget;

    @NotNull(message = "Tür ID'si zorunludur.")
    private Long speciesId;

    @NotNull(message = "Aşı tipi ID'si zorunludur.")
    private Long vaccineTypeId;

    private String notes;
}