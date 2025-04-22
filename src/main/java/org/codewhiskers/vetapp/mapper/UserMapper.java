package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.User.request.UserRequestDTO;
import org.codewhiskers.vetapp.dto.User.response.UserResponseDTO;
import org.codewhiskers.vetapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {SpecializationMapper.class})
public interface UserMapper {
    UserResponseDTO userToResponseDTO(User user);

    @Mapping(target = "specialization", ignore = true)
    User requestDTOToUser(UserRequestDTO userRequestDTO);

    @Mapping(target = "specialization", ignore = true)
    void updateUserFromRequestDTO(UserRequestDTO userRequestDTO, @MappingTarget User user);
}
