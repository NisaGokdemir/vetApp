package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.UsageArea.request.UsageAreaRequestDTO;
import org.codewhiskers.vetapp.dto.UsageArea.response.UsageAreaResponseDTO;
import org.codewhiskers.vetapp.entity.UsageArea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsageAreaMapper {

     UsageAreaResponseDTO toDto(UsageArea usersArea);

    @Mapping(target = "id", ignore = true)
    UsageArea toEntity(UsageAreaRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    void toUpdateEntity(UsageAreaRequestDTO usageAreaRequestDTO, @MappingTarget UsageArea usageArea);
}