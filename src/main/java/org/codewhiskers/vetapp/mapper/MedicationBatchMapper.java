package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.MedicationBatch.request.MedicationBatchRequestDTO;
import org.codewhiskers.vetapp.dto.MedicationBatch.response.MedicationBatchResponseDTO;
import org.codewhiskers.vetapp.entity.Clinic;
import org.codewhiskers.vetapp.entity.Drug;
import org.codewhiskers.vetapp.entity.Medication;
import org.codewhiskers.vetapp.entity.MedicationBatch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {MedicationMapper.class, DrugMapper.class, ClinicMapper.class})
public interface MedicationBatchMapper {
    MedicationBatchResponseDTO toDto(MedicationBatch medicationBatch);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "medication", source = "medication")
    @Mapping(target = "drug", source = "drug")
    @Mapping(target = "clinic", source = "clinic")
    MedicationBatch toEntity(MedicationBatchRequestDTO dto, Medication medication, Drug drug, Clinic clinic);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "medication", source = "medication")
    @Mapping(target = "drug", source = "drug")
    @Mapping(target = "clinic", source = "clinic")
    void toUpdateEntity(MedicationBatchRequestDTO dto, @MappingTarget MedicationBatch medicationBatch, Medication medication, Drug drug, Clinic clinic);
}
