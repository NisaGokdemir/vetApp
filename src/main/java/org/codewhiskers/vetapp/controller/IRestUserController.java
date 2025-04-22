package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.dto.User.request.UserRequestDTO;
import org.codewhiskers.vetapp.dto.User.response.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IRestUserController {
    ResponseEntity<Page<UserResponseDTO>> getAllUsers(int page, int size);

    ResponseEntity<UserResponseDTO> getUserById(Long id);

    ResponseEntity<UserResponseDTO> createUser(UserRequestDTO userRequestDTO);

    ResponseEntity<UserResponseDTO> updateUser(Long id, UserRequestDTO userRequestDTO);

    void deleteUser(Long id);
}
