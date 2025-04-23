package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.PrescriptionItem.request.PrescriptionItemRequestDTO;
import org.codewhiskers.vetapp.dto.PrescriptionItem.response.PrescriptionItemResponseDTO;
import org.codewhiskers.vetapp.entity.PrescriptionItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {
        PrescriptionMapper.class,
        MedicationMapper.class})
public interface PrescriptionItemMapper {

    PrescriptionItemResponseDTO toPrescriptionItemResponseDto(PrescriptionItem prescriptionItem);
    PrescriptionItem toPrescriptionItem(PrescriptionItemRequestDTO dto);
    void updatePrescriptionItemFromDto(PrescriptionItemRequestDTO dto, @MappingTarget PrescriptionItem prescriptionItem);
}
