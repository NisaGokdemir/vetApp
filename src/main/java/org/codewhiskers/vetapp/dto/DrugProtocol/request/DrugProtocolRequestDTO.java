package org.codewhiskers.vetapp.dto.DrugProtocol.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DrugProtocolRequestDTO {

    @NotNull(message = "İlaç ID boş olamaz.")
    private Long drugId;

    @NotNull(message = "Tür ID boş olamaz.")
    private Long speciesId;

    @Min(value = 0, message = "Minimum yaş negatif olamaz.")
    private Integer minAge;

    @DecimalMin(value = "0.0", inclusive = false, message = "Doz miktarı pozitif olmalıdır.")
    private Double maxDoseMgKg;

    @NotBlank(message = "Sıklık bilgisi girilmelidir.")
    private String frequency;

    private String notes;
}
