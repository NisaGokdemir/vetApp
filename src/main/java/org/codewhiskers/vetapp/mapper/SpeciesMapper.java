package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.Species.request.SpeciesRequestDTO;
import org.codewhiskers.vetapp.dto.Species.response.SpeciesResponseDTO;
import org.codewhiskers.vetapp.entity.Species;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {FamilyMapper.class})
public interface SpeciesMapper {

    SpeciesResponseDTO toSpeciesResponseDto(Species species);

    @Mapping(target = "id", ignore = true)
    Species toSpeciesEntity(SpeciesRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateSpeciesEntity(SpeciesRequestDTO dto, @MappingTarget Species species);
}
