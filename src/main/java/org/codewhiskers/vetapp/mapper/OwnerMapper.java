package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.Owner.request.OwnerRequestDTO;
import org.codewhiskers.vetapp.dto.Owner.response.OwnerResponseDTO;
import org.codewhiskers.vetapp.entity.Owner;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface OwnerMapper {

    OwnerResponseDTO toOwnerResponseDto(Owner owner);

    @Mapping(target = "id", ignore = true)
    Owner toOwnerEntity(OwnerRequestDTO ownerRequestDTO);

    @Mapping(target = "id", ignore = true)
    void updateOwnerEntity(OwnerRequestDTO dto, @MappingTarget Owner owner);
}
