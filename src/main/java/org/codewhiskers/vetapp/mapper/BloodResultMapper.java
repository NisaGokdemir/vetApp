package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.BloodResult.request.BloodResultRequestDTO;
import org.codewhiskers.vetapp.dto.BloodResult.response.BloodResultResponseDTO;
import org.codewhiskers.vetapp.entity.BloodResult;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ClinicMapper.class, PatientMapper.class})
public interface BloodResultMapper {

    BloodResultResponseDTO toBloodResultResponseDTO(BloodResult bloodResult);
    BloodResult toBloodResult(BloodResultRequestDTO bloodResultRequestDTO);
    void updateBloodResultFromDTO(BloodResultRequestDTO bloodResultRequestDTO, @MappingTarget BloodResult bloodResult);
}
