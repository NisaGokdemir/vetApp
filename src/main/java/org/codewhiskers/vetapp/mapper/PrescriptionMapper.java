package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.Prescription.request.PrescriptionRequestDTO;
import org.codewhiskers.vetapp.dto.Prescription.response.PrescriptionResponseDTO;
import org.codewhiskers.vetapp.entity.Prescription;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {DiagnosisMapper.class})
public interface PrescriptionMapper {

    PrescriptionResponseDTO prescriptionToResponseDTO(Prescription prescription);
    Prescription requestDTOToPrescription(PrescriptionRequestDTO prescriptionRequestDTO);
    void updatePrescriptionFromRequestDTO(PrescriptionRequestDTO prescriptionRequestDTO, @MappingTarget Prescription prescription);
}
