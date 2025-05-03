package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.VaccineList.request.VaccineListRequestDTO;
import org.codewhiskers.vetapp.dto.VaccineList.response.VaccineListResponseDTO;
import org.codewhiskers.vetapp.entity.Species;
import org.codewhiskers.vetapp.entity.Vaccination;
import org.codewhiskers.vetapp.entity.VaccineList;
import org.codewhiskers.vetapp.entity.VaccineType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", 
        uses = {VaccinationMapper.class, SpeciesMapper.class, VaccineTypeMapper.class},
        implementationName = "vaccineListMapperImpl",
        implementationPackage = "org.codewhiskers.vetapp.mapper.impl",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VaccineListMapper {

    @Mapping(target = "vaccination", source = "vaccination")
    @Mapping(target = "species", source = "species")
    @Mapping(target = "vaccineType", source = "vaccineType")
    VaccineListResponseDTO toDto(VaccineList vaccineList);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vaccination", source = "vaccination")
    @Mapping(target = "species", source = "species")
    @Mapping(target = "vaccineType", source = "vaccineType")
    VaccineList toEntity(VaccineListRequestDTO dto, Vaccination vaccination, Species species, VaccineType vaccineType);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vaccination", source = "vaccination")
    @Mapping(target = "species", source = "species")
    @Mapping(target = "vaccineType", source = "vaccineType")
    void toUpdateEntity(VaccineListRequestDTO dto, @MappingTarget VaccineList vaccineList, Vaccination vaccination, Species species, VaccineType vaccineType);
}
