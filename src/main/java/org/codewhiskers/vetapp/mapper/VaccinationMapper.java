package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.Vaccination.request.VaccinationRequestDTO;
import org.codewhiskers.vetapp.dto.Vaccination.response.VaccinationResponseDTO;
import org.codewhiskers.vetapp.entity.Vaccination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VaccinationMapper {

     VaccinationResponseDTO toDto(Vaccination dto);

    @Mapping(target = "id", ignore = true)
    Vaccination toEntity(VaccinationRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    void toUpdateEntity(VaccinationRequestDTO dto, @MappingTarget Vaccination vaccination);
}