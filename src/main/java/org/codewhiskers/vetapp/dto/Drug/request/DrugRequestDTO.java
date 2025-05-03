package org.codewhiskers.vetapp.dto.Drug.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DrugRequestDTO {

    @NotBlank(message = "İlaç ismi boş olamaz.")
    private String name;
}