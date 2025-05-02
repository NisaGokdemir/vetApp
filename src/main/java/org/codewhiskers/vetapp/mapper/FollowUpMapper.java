package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.FollowUp.request.FollowUpRequestDTO;
import org.codewhiskers.vetapp.dto.FollowUp.response.FollowUpResponseDTO;
import org.codewhiskers.vetapp.entity.FollowUp;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {DiagnosisMapper.class})
public interface FollowUpMapper {
    FollowUpResponseDTO toFollowUp(FollowUp followUp);
    FollowUp toFollowUpEntity(FollowUpRequestDTO followUpRequestDTO);
    void updateFollowUpEntity(FollowUpRequestDTO followUpRequestDTO, @MappingTarget FollowUp followUp);
}
