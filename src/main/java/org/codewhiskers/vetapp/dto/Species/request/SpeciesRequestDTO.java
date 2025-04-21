package org.codewhiskers.vetapp.dto.Species.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SpeciesRequestDTO {

    @NotBlank(message = "Tür adı boş olamaz.")
    @Size(min = 2, max = 50, message = "Tür adı 2 ile 50 karakter arasında olmalıdır.")
    private String name;
}