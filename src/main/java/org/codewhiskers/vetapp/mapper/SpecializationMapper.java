package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.Specialization.request.SpecializationRequestDTO;
import org.codewhiskers.vetapp.dto.Specialization.response.SpecializationResponseDTO;
import org.codewhiskers.vetapp.entity.Specialization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SpecializationMapper {

    SpecializationResponseDTO specializationToResponseDTO(Specialization specialization);
    Specialization requestDTOToSpecialization(SpecializationRequestDTO specializationRequestDTO);

    void updateSpecializationFromRequestDTO(SpecializationRequestDTO specializationRequestDTO,@MappingTarget Specialization specialization);

}
