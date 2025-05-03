package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.bloodType.request.BloodTypeRequestDTO;
import org.codewhiskers.vetapp.dto.bloodType.response.BloodTypeResponseDTO;
import org.codewhiskers.vetapp.entity.BloodType;
import org.codewhiskers.vetapp.entity.Species;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = SpeciesMapper.class)
public interface BloodTypeMapper {

    BloodTypeResponseDTO toBloodTypeResponseDto(BloodType bloodType);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "species", source = "species")
    BloodType toBloodTypeEntity(BloodTypeRequestDTO dto, Species species);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "species", source = "species")
    void updateBloodTypeEntity(BloodTypeRequestDTO dto, @MappingTarget BloodType bloodType, Species species);
}