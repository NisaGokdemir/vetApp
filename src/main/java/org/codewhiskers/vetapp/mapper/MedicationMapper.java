package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.Medication.request.MedicationRequestDTO;
import org.codewhiskers.vetapp.dto.Medication.response.MedicationResponseDTO;
import org.codewhiskers.vetapp.entity.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MedicationMapper {

    Medication requestDTOToMedication(MedicationRequestDTO medicationRequestDTO);
    MedicationResponseDTO medicationToResponseDTO(Medication medication);

    void updateMedicationFromRequestDTO(MedicationRequestDTO medicationRequestDTO, @MappingTarget Medication medication);
}
