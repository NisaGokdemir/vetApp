package org.codewhiskers.vetapp.dto.bloodType.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BloodTypeRequestDTO {

    @NotBlank(message = "Kan tipi boş olamaz.")
    @Size(min = 1, max = 10, message = "Kan tipi 1 ile 10 karakter arasında olmalıdır.")
    private String type;

    @NotNull(message = "Tür ID'si boş olamaz.")
    private Long speciesId;
}