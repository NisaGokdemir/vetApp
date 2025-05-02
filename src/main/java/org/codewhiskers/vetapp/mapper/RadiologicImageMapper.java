package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.RadiologicImage.request.RadiologicImageRequestDTO;
import org.codewhiskers.vetapp.dto.RadiologicImage.response.RadiologicImageResponseDTO;
import org.codewhiskers.vetapp.entity.RadiologicImage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {PatientMapper.class, ClinicMapper.class})
public interface RadiologicImageMapper {

    RadiologicImageResponseDTO radiologicImageToResponseDTO(RadiologicImage radiologicImage);
    RadiologicImage requestDTOToRadiologicImage(RadiologicImageRequestDTO radiologicImageRequestDTO);
    void updateRadiologicImageFromRequestDTO(RadiologicImageRequestDTO radiologicImageRequestDTO, @MappingTarget RadiologicImage radiologicImage);
}
