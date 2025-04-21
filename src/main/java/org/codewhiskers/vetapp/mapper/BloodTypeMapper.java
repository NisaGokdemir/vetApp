package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.bloodType.request.BloodTypeRequestDTO;
import org.codewhiskers.vetapp.dto.bloodType.response.BloodTypeResponseDTO;
import org.codewhiskers.vetapp.entity.BloodType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = SpeciesMapper.class)
public interface BloodTypeMapper {


}