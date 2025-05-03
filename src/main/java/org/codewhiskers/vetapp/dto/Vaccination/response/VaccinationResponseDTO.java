package org.codewhiskers.vetapp.dto.Vaccination.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaccinationResponseDTO {
    private Long id;
    private String name;
    private String vaccinationUnit;
}