package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.Patient.request.PatientRequestDTO;
import org.codewhiskers.vetapp.dto.Patient.response.PatientResponseDTO;
import org.codewhiskers.vetapp.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {OwnerMapper.class, SpeciesMapper.class, BreedMapper.class, BloodTypeMapper.class})
public interface PatientMapper {

    PatientResponseDTO toPatientResponseDto(Patient patient);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", source = "owner")
    @Mapping(target = "species", source = "species")
    @Mapping(target = "breed", source = "breed")
    @Mapping(target = "bloodType", source = "bloodType")
    Patient toPatientEntity(PatientRequestDTO dto,
                            Owner owner,
                            Species species,
                            Breed breed,
                            BloodType bloodType);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", source = "owner")
    @Mapping(target = "species", source = "species")
    @Mapping(target = "breed", source = "breed")
    @Mapping(target = "bloodType", source = "bloodType")
    void updatePatientEntity(PatientRequestDTO dto,
                             @MappingTarget Patient patient,
                             Owner owner,
                             Species species,
                             Breed breed,
                             BloodType bloodType);
}
