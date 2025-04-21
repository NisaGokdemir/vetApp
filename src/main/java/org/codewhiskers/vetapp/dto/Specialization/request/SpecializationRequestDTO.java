package org.codewhiskers.vetapp.dto.Specialization.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecializationRequestDTO {
    @NotBlank
    private String name;
}
