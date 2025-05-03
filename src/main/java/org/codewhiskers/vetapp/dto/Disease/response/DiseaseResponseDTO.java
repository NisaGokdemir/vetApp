package org.codewhiskers.vetapp.dto.Disease.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiseaseResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String symptoms;
    private String category;
    private String animalTypes;
} 