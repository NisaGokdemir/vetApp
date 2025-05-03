package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.User.request.UserRequestDTO;
import org.codewhiskers.vetapp.dto.User.response.UserResponseDTO;
import org.codewhiskers.vetapp.entity.Clinic;
import org.codewhiskers.vetapp.entity.Specialization;
import org.codewhiskers.vetapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ClinicMapper.class, SpecializationMapper.class})
public interface UserMapper {
    UserResponseDTO userToResponseDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clinic", source = "clinic")
    @Mapping(target = "specialization", source = "specialization")
    User requestDTOToUser(UserRequestDTO userRequestDTO, Clinic clinic, Specialization specialization);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clinic", source = "clinic")
    @Mapping(target = "specialization", source = "specialization")
    void updateUserFromRequestDTO(UserRequestDTO userRequestDTO, @MappingTarget User user, Clinic clinic, Specialization specialization);
}
