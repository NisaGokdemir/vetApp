package org.codewhiskers.vetapp.dto.Drug.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrugRequestDTO {
    @NotBlank(message = "İlaç adı boş olamaz")
    private String name;
}