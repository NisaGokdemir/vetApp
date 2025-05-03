package org.codewhiskers.vetapp.mapper;
import org.codewhiskers.vetapp.dto.Drug.request.DrugRequestDTO;
import org.codewhiskers.vetapp.dto.Drug.response.DrugResponseDTO;
import org.codewhiskers.vetapp.entity.Drug;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DrugMapper {
    DrugResponseDTO toResponseDTO(Drug drug);

    @Mapping(target = "id", ignore = true)
    Drug toEntity(DrugRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateDrugFromDTO(DrugRequestDTO dto, @MappingTarget Drug drug);
}