package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.Medication.request.MedicationRequestDTO;
import org.codewhiskers.vetapp.dto.Medication.response.MedicationResponseDTO;
import org.codewhiskers.vetapp.entity.Medication;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.MedicationMapper;
import org.codewhiskers.vetapp.repository.MedicationRepository;
import org.codewhiskers.vetapp.service.IMedicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements IMedicationService {

    private final MedicationRepository medicationRepository;
    private final MedicationMapper medicationMapper;

    private Medication findMedicationById(Long id) {
        return medicationRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()))
        );
    }

    @Override
    public Page<MedicationResponseDTO> getAllMedications(int page, int size) {
        Page<Medication> medications = medicationRepository.findAll(PageRequest.of(page, size));
        if(!medications.hasContent()){
            throw new BaseException(new ErrorMessage(MessageType.RECORDS_NOT_FOUND,""));
        }
        return medications.map(medicationMapper::medicationToResponseDTO);
    }

    @Override
    public MedicationResponseDTO getMedicationById(Long id) {
        Medication medication = findMedicationById(id);
        return medicationMapper.medicationToResponseDTO(medication);
    }

    @Override
    public void DeleteMedicationById(Long id) {
        Medication medication = findMedicationById(id);
        medicationRepository.delete(medication);
        if(medicationRepository.existsById(id)){
            throw new BaseException(new ErrorMessage(MessageType.RECORD_DELETE_UNSUCCESS,id.toString()));
        }
    }

    @Override
    public MedicationResponseDTO createMedication(MedicationRequestDTO medicationRequestDTO) {
        Medication medication = medicationMapper.requestDTOToMedication(medicationRequestDTO);
        medicationRepository.save(medication);
        if(medication.getName() == null || medication.getName().isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.RECORD_CREATE_UNSUCCESS,medication.getName()));
        }
        return medicationMapper.medicationToResponseDTO(medication);
    }

    @Override
    public MedicationResponseDTO updateMedication(Long id, MedicationRequestDTO requestDTO) {
        Medication medication = findMedicationById(id);
        medicationMapper.updateMedicationFromRequestDTO(requestDTO, medication);
        medicationRepository.save(medication);
        if(medication.getName() == null || medication.getName().isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.RECORD_UPDATE_UNSUCCESS,medication.getName()));
        }
        return medicationMapper.medicationToResponseDTO(medication);
    }
}
