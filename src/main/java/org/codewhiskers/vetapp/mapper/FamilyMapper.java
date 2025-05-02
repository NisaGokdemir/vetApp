package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.Family.request.FamilyRequestDTO;
import org.codewhiskers.vetapp.dto.Family.response.FamilyResponseDTO;
import org.codewhiskers.vetapp.entity.Family;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FamilyMapper {

    FamilyResponseDTO toFamilyResponseDto(Family family);
    Family toFamilyEntity(FamilyRequestDTO dto);
    void updateFamilyEntity(FamilyRequestDTO dto, @MappingTarget Family family);
}
