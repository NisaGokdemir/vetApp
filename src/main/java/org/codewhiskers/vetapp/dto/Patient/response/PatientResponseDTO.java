package org.codewhiskers.vetapp.dto.Patient.response;

import lombok.Data;
import org.codewhiskers.vetapp.dto.Breed.response.BreedResponseDTO;
import org.codewhiskers.vetapp.dto.Family.response.FamilyResponseDTO;
import org.codewhiskers.vetapp.dto.Owner.response.OwnerResponseDTO;
import org.codewhiskers.vetapp.dto.Species.response.SpeciesResponseDTO;
import org.codewhiskers.vetapp.dto.bloodType.response.BloodTypeResponseDTO;
import org.codewhiskers.vetapp.entity.BloodType;

import java.time.LocalDateTime;

@Data
public class PatientResponseDTO {
    private Long id;
    private String name;
    private Integer age;
    private Double weight;
    private String chipNumber;
    private BloodTypeResponseDTO bloodType;
    private OwnerResponseDTO owner;
    private SpeciesResponseDTO species;
    private BreedResponseDTO breed;
    private FamilyResponseDTO family;
}