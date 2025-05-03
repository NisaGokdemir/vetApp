package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.VaccinationInventory.request.VaccinationInventoryRequestDTO;
import org.codewhiskers.vetapp.dto.VaccinationInventory.response.VaccinationInventoryResponseDTO;
import org.codewhiskers.vetapp.entity.Clinic;
import org.codewhiskers.vetapp.entity.Vaccination;
import org.codewhiskers.vetapp.entity.VaccinationInventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", 
        uses = {VaccinationMapper.class, ClinicMapper.class},
        implementationName = "vaccinationInventoryMapperImpl",
        implementationPackage = "org.codewhiskers.vetapp.mapper.impl",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VaccinationInventoryMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "vaccination", source = "vaccination")
    @Mapping(target = "clinic", source = "clinic")
    VaccinationInventoryResponseDTO toDto(VaccinationInventory entity);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vaccination", source = "vaccination")
    @Mapping(target = "clinic", source = "clinic")
    VaccinationInventory toEntity(VaccinationInventoryRequestDTO dto, Vaccination vaccination, Clinic clinic);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vaccination", source = "vaccination")
    @Mapping(target = "clinic", source = "clinic")
    void toUpdateEntity(VaccinationInventoryRequestDTO dto, @MappingTarget VaccinationInventory entity, Vaccination vaccination, Clinic clinic);
}