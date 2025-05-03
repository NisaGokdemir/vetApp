package org.codewhiskers.vetapp.dto.UsageArea.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsageAreaRequestDTO {
    @NotBlank(message = "Kullanım alanı adı zorunludur.")
    private String name;
}