package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.VaccineType.request.VaccineTypeRequestDTO;
import org.codewhiskers.vetapp.dto.VaccineType.response.VaccineTypeResponseDTO;
import org.codewhiskers.vetapp.entity.VaccineType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, implementationName = "vaccineTypeMapperImpl", implementationPackage = "org.codewhiskers.vetapp.mapper.impl")
public interface VaccineTypeMapper {
    VaccineTypeResponseDTO toDto(VaccineType entity);

    @Mapping(target = "id", ignore = true)
    VaccineType toEntity(VaccineTypeRequestDTO requestDTO);

    @Mapping(target = "id", ignore = true)
    void toUpdateEntity(VaccineTypeRequestDTO requestDTO, @MappingTarget VaccineType entity);
}
