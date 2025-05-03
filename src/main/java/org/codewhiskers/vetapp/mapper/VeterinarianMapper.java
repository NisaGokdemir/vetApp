package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.Veterinarian.request.VeterinarianRequestDTO;
import org.codewhiskers.vetapp.dto.Veterinarian.response.VeterinarianResponseDTO;
import org.codewhiskers.vetapp.entity.Veterinarian;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class, SpecializationMapper.class, ClinicMapper.class})
public interface VeterinarianMapper {
    VeterinarianResponseDTO toResponseDTO(Veterinarian veterinarian);
    Veterinarian toEntity(VeterinarianRequestDTO veterinarianRequestDTO);
    void updateVeterinarianFromDTO(VeterinarianRequestDTO veterinarianRequestDTO, @MappingTarget Veterinarian veterinarian);
}
