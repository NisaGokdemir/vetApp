package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.Breed.request.BreedRequestDTO;
import org.codewhiskers.vetapp.dto.Breed.response.BreedResponseDTO;
import org.codewhiskers.vetapp.entity.Breed;
import org.codewhiskers.vetapp.entity.Species;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {SpeciesMapper.class})
public interface BreedMapper {

    BreedResponseDTO toBreedResponseDto(Breed breed);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "species", source = "species")
    Breed toBreedEntity(BreedRequestDTO dto, Species species);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "species", source = "species")
    void updateBreedEntity(BreedRequestDTO dto, @MappingTarget Breed breed, Species species);
}
