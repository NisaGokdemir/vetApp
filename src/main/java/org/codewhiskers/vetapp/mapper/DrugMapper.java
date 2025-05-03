package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.Drug.request.DrugRequestDTO;
import org.codewhiskers.vetapp.dto.Drug.response.DrugResponseDTO;
import org.codewhiskers.vetapp.entity.Drug;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DrugMapper {
    Drug toEntity(DrugRequestDTO drugRequestDTO);
    DrugResponseDTO toDto(Drug drug);
    void toUpdateEntity(DrugRequestDTO drugRequestDTO,@MappingTarget Drug drug);
}