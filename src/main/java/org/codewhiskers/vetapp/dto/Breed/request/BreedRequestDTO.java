package org.codewhiskers.vetapp.dto.Breed.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BreedRequestDTO {

    @NotBlank(message = "Irk adı boş olamaz.")
    @Size(min = 2, max = 50, message = "Irk adı 2 ile 50 karakter arasında olmalıdır.")
    private String name;

    @NotNull(message = "Tür ID'si boş olamaz.")
    private Long speciesId;
}