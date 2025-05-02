package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.Clinic.request.ClinicRequestDTO;
import org.codewhiskers.vetapp.dto.Clinic.response.ClinicResponseDTO;
import org.codewhiskers.vetapp.entity.Clinic;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClinicMapper {

    ClinicResponseDTO toClinicResponseDto(Clinic clinic);
    Clinic toClinicEntity(ClinicRequestDTO dto);
    void updateClinicEntity(ClinicRequestDTO dto, @MappingTarget Clinic clinic);
}
