package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.User.request.UserRequestDTO;
import org.codewhiskers.vetapp.dto.User.response.UserResponseDTO;
import org.codewhiskers.vetapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO userToResponseDTO(User user);

    User requestDTOToUser(UserRequestDTO userRequestDTO);

    void updateUserFromRequestDTO(UserRequestDTO userRequestDTO, @MappingTarget User user);
}
