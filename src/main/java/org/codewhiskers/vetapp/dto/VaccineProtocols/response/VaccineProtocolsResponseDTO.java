package org.codewhiskers.vetapp.dto.VaccineProtocols.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Species.response.SpeciesResponseDTO;
import org.codewhiskers.vetapp.dto.Vaccination.response.VaccinationResponseDTO;

@Getter
@Setter
public class VaccineProtocolsResponseDTO {
    private Long id;
    private VaccinationResponseDTO vaccination;
    private String brand;
    private SpeciesResponseDTO species;
    private Integer firstDoseWeek;
    private Integer boosterInterval;
    private Boolean isWeightBased;
    private Double dosePerKg;
    private Double maxSingleDose;
}