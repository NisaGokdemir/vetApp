package org.codewhiskers.vetapp.dto.Family.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamilyRequestDTO {

    @NotBlank(message = "Family name is required")
    private String name;
}
