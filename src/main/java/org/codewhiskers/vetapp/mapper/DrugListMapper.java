package org.codewhiskers.vetapp.mapper;
import org.codewhiskers.vetapp.dto.DrugList.request.DrugListRequestDTO;
import org.codewhiskers.vetapp.dto.DrugList.response.DrugListResponseDTO;
import org.codewhiskers.vetapp.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {DrugMapper.class, UsageAreaMapper.class, SpeciesMapper.class})
public interface DrugListMapper {

    DrugListResponseDTO toDto(DrugList drugList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "drug", source = "drug")
    @Mapping(target = "usageArea", source = "usageArea")
    @Mapping(target = "species", source = "species")
    DrugList toEntity(DrugListRequestDTO dto, Drug drug, UsageArea usageArea, Species species);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "drug", source = "drug")
    @Mapping(target = "usageArea", source = "usageArea")
    @Mapping(target = "species", source = "species")
    void toUpdateEntity(DrugListRequestDTO dto, @MappingTarget DrugList drugList, Drug drug, UsageArea usageArea, Species species);
}