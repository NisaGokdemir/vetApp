package org.codewhiskers.vetapp.mapper;
import org.codewhiskers.vetapp.dto.DrugProtocol.request.DrugProtocolRequestDTO;
import org.codewhiskers.vetapp.dto.DrugProtocol.response.DrugProtocolResponseDTO;
import org.codewhiskers.vetapp.entity.Drug;
import org.codewhiskers.vetapp.entity.DrugProtocol;
import org.codewhiskers.vetapp.entity.Species;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {DrugMapper.class, SpeciesMapper.class})
public interface DrugProtocolMapper {

    DrugProtocolResponseDTO toResponseDTO(DrugProtocol protocol);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "drug", source = "drug")
    @Mapping(target = "species", source = "species")
    DrugProtocol toEntity(DrugProtocolRequestDTO dto, Drug drug, Species species);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "drug", source = "drug")
    @Mapping(target = "species", source = "species")
    void updateEntity(DrugProtocolRequestDTO dto, @MappingTarget DrugProtocol protocol, Drug drug, Species species);
}