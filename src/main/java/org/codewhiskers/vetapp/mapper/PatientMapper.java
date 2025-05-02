package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.Patient.request.PatientRequestDTO;
import org.codewhiskers.vetapp.dto.Patient.response.PatientResponseDTO;
import org.codewhiskers.vetapp.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {OwnerMapper.class, SpeciesMapper.class, BreedMapper.class, BloodTypeMapper.class, FamilyMapper.class })
public interface PatientMapper {

    PatientResponseDTO toPatientResponseDto(Patient patient);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "age", source = "dto.age")
    @Mapping(target = "weight", source = "dto.weight")
    @Mapping(target = "chipNumber", source = "dto.chipNumber")
    @Mapping(target = "bloodType", source = "bloodType")
    @Mapping(target = "owner", source = "owner")
    @Mapping(target = "species", source = "species")
    @Mapping(target = "breed", source = "breed")
    @Mapping(target = "family", source = "family")
    Patient toPatientEntity(PatientRequestDTO dto,
                            Owner owner,
                            Species species,
                            Breed breed,
                            BloodType bloodType,
                            Family family);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "age", source = "dto.age")
    @Mapping(target = "weight", source = "dto.weight")
    @Mapping(target = "chipNumber", source = "dto.chipNumber")
    @Mapping(target = "bloodType", source = "bloodType")
    @Mapping(target = "owner", source = "owner")
    @Mapping(target = "species", source = "species")
    @Mapping(target = "breed", source = "breed")
    @Mapping(target = "family", source = "family")
    void updatePatientEntity(PatientRequestDTO dto,
                             @MappingTarget Patient patient,
                             Owner owner,
                             Species species,
                             Breed breed,
                             BloodType bloodType,
                             Family family);
}
