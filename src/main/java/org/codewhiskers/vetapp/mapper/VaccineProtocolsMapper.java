package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.VaccineProtocols.request.VaccineProtocolsRequestDTO;
import org.codewhiskers.vetapp.dto.VaccineProtocols.response.VaccineProtocolsResponseDTO;
import org.codewhiskers.vetapp.entity.Species;
import org.codewhiskers.vetapp.entity.Vaccination;
import org.codewhiskers.vetapp.entity.VaccineProtocols;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {VaccinationMapper.class, SpeciesMapper.class})
public interface VaccineProtocolsMapper {

    @Named("toDto")
    VaccineProtocolsResponseDTO toDto(VaccineProtocols protocol);

    @Named("toEntity")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vaccination", source = "vaccination")
    @Mapping(target = "species", source = "species")
    VaccineProtocols toEntity(VaccineProtocolsRequestDTO dto, Vaccination vaccination, Species species);

    @Named("toUpdateEntity")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vaccination", source = "vaccination")
    @Mapping(target = "species", source = "species")
    void toUpdateEntity(VaccineProtocolsRequestDTO dto, @MappingTarget VaccineProtocols protocol, Vaccination vaccination, Species species);
}
