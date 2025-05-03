package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.User.request.UserRequestDTO;
import org.codewhiskers.vetapp.dto.User.response.UserResponseDTO;
import org.codewhiskers.vetapp.entity.Specialization;
import org.codewhiskers.vetapp.entity.User;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.UserMapper;
import org.codewhiskers.vetapp.repository.SpecializationRepository;
import org.codewhiskers.vetapp.repository.UserRepository;
import org.codewhiskers.vetapp.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final SpecializationRepository specializationRepository;
    private final UserMapper userMapper;

    private Specialization findSpecializationById(Long id) {
        return specializationRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()))
        );
    }

    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()))
        );
    }

    @Override
    public Page<UserResponseDTO> getAllUsers(int page, int size) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, size));
        if (!users.hasContent()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORDS_NOT_FOUND,""));
        }
        return users.map(userMapper::userToResponseDTO);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = findUserById(id);
        return userMapper.userToResponseDTO(user);
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        User user = userMapper.requestDTOToUser(userRequestDTO);
        if(user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_CREATE_UNSUCCESS,""));
        }
        user = userRepository.save(user);
        return userMapper.userToResponseDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = findUserById(id);
        userMapper.updateUserFromRequestDTO(userRequestDTO, user);
        user = userRepository.save(user);
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_UPDATE_UNSUCCESS,id.toString()));
        }
        return userMapper.userToResponseDTO(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = findUserById(id);
        userRepository.delete(user);
        if (userRepository.existsById(id)) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_DELETE_UNSUCCESS,id.toString()));
        }
    }
}
