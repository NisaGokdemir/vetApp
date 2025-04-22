package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.MedicationBatch.request.MedicationBatchRequestDTO;
import org.codewhiskers.vetapp.dto.MedicationBatch.response.MedicationBatchResponseDTO;
import org.codewhiskers.vetapp.entity.MedicationBatch;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {MedicationMapper.class})
public interface MedicationBatchMapper {

    MedicationBatch requestDTOToMedicationBatch(MedicationBatchRequestDTO medicationBatchRequestDTO);
    MedicationBatchResponseDTO medicationBatchToResponseDTO(MedicationBatch medicationBatch);

    void updateMedicationBatchFromRequestDTO(MedicationBatchRequestDTO medicationBatchRequestDTO, @MappingTarget MedicationBatch medicationBatch);
}
