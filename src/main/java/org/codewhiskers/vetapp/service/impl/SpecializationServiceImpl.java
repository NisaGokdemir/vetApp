package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.Specialization.request.SpecializationRequestDTO;
import org.codewhiskers.vetapp.dto.Specialization.response.SpecializationResponseDTO;
import org.codewhiskers.vetapp.entity.Specialization;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.SpecializationMapper;
import org.codewhiskers.vetapp.repository.SpecializationRepository;
import org.codewhiskers.vetapp.service.ISpecializationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecializationServiceImpl implements ISpecializationService {

    private final SpecializationRepository specializationRepository;
    private final SpecializationMapper specializationMapper;


    private Specialization findSpecializationById(Long id) {
        return specializationRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()))
        );
    }

    @Override
    public Page<SpecializationResponseDTO> getAllSpecializations(int page, int size) {
        Page<Specialization> specializations = specializationRepository.findAll(PageRequest.of(page, size));
        if (!specializations.hasContent()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORDS_NOT_FOUND,""));
        }
        return specializations.map(specializationMapper::specializationToResponseDTO);
    }

    @Override
    public SpecializationResponseDTO createSpecialization(SpecializationRequestDTO specializationRequestDTO) {
         Specialization specialization = specializationMapper.requestDTOToSpecialization(specializationRequestDTO);
         if(specialization.getName() == null || specialization.getName().isEmpty()) {
             throw new BaseException(new ErrorMessage(MessageType.RECORD_CREATE_UNSUCCESS,""));
         }
         specialization = specializationRepository.save(specialization);
         return specializationMapper.specializationToResponseDTO(specialization);
    }

    @Override
    public SpecializationResponseDTO updateSpecialization(Long id, SpecializationRequestDTO specializationRequestDTO) {
        Specialization specialization = findSpecializationById(id);
        specializationMapper.updateSpecializationFromRequestDTO(specializationRequestDTO, specialization);
        specialization = specializationRepository.save(specialization);
        if (specialization.getName() == null || specialization.getName().isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_UPDATE_UNSUCCESS,id.toString()));
        }
        return specializationMapper.specializationToResponseDTO(specialization);
    }

    @Override
    public void deleteSpecialization(Long id) {
        Specialization specialization = findSpecializationById(id);
        specializationRepository.delete(specialization);
        if (specializationRepository.existsById(id)) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_DELETE_UNSUCCESS,id.toString()));
        }
    }

    @Override
    public SpecializationResponseDTO getSpecializationById(Long id) {
        Specialization specialization = findSpecializationById(id);
        return specializationMapper.specializationToResponseDTO(specialization);
    }
}
