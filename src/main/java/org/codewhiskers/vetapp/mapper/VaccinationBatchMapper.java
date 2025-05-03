package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.VaccinationBatches.request.VaccinationBatchesRequestDTO;
import org.codewhiskers.vetapp.dto.VaccinationBatches.response.VaccinationBatchesResponseDTO;
import org.codewhiskers.vetapp.entity.Clinic;
import org.codewhiskers.vetapp.entity.Vaccination;
import org.codewhiskers.vetapp.entity.VaccinationBatch;
import org.codewhiskers.vetapp.entity.VaccinationInventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {VaccinationInventoryMapper.class, VaccinationMapper.class, ClinicMapper.class})
public interface VaccinationBatchMapper {

    @Named("toDto")
    VaccinationBatchesResponseDTO toDto(VaccinationBatch vaccinationBatch);

    @Named("toEntity")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "vaccinationInventory", target = "vaccinationInventory")
    @Mapping(source = "vaccination", target = "vaccination")
    @Mapping(source = "clinic", target = "clinic")
    VaccinationBatch toEntity(VaccinationBatchesRequestDTO dto, VaccinationInventory vaccinationInventory, Vaccination vaccination, Clinic clinic);

    @Named("toUpdateEntity")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "vaccinationInventory", target = "vaccinationInventory")
    @Mapping(source = "vaccination", target = "vaccination")
    @Mapping(source = "clinic", target = "clinic")
    void toUpdateEntity(VaccinationBatchesRequestDTO dto, @MappingTarget VaccinationBatch vaccinationBatch, VaccinationInventory vaccinationInventory, Vaccination vaccination, Clinic clinic);
} 