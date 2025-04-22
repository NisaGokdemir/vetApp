package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.MedicationBatch.request.MedicationBatchRequestDTO;
import org.codewhiskers.vetapp.dto.MedicationBatch.response.MedicationBatchResponseDTO;
import org.codewhiskers.vetapp.entity.Medication;
import org.codewhiskers.vetapp.entity.MedicationBatch;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.MedicationBatchMapper;
import org.codewhiskers.vetapp.repository.MedicationBatchRepository;
import org.codewhiskers.vetapp.repository.MedicationRepository;
import org.codewhiskers.vetapp.service.IMedicationBatchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicationBatchServiceImpl implements IMedicationBatchService {

    private final MedicationBatchRepository medicationBatchRepository;
    private final MedicationRepository medicationRepository;
    private final MedicationBatchMapper medicationBatchMapper;

    private MedicationBatch findMedicationBatchById(Long id) {
        return medicationBatchRepository.findById(id)
                .orElseThrow(
                        () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()))
                );
    }

    private Medication findMedicationById(Long id) {
        return medicationRepository.findById(id)
                .orElseThrow(
                        () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()))
                );
    }


    @Override
    public Page<MedicationBatchResponseDTO> getAllMedicationBatch(Pageable pageable) {
        Page<MedicationBatch> medicationBatches = medicationBatchRepository.findAll(pageable);
        if (!medicationBatches.hasContent()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORDS_NOT_FOUND,""));
        }
        return medicationBatches.map(medicationBatchMapper::medicationBatchToResponseDTO);
    }

    @Override
    public MedicationBatchResponseDTO getMedicationBatch(Long id) {
        MedicationBatch medicationBatch = findMedicationBatchById(id);
        return medicationBatchMapper.medicationBatchToResponseDTO(medicationBatch);
    }

    @Override
    public MedicationBatchResponseDTO createMedicationBatch(MedicationBatchRequestDTO medicationBatchRequestDTO) {
        MedicationBatch medicationBatch = medicationBatchMapper.requestDTOToMedicationBatch(medicationBatchRequestDTO);
        Medication medication = findMedicationById(medicationBatchRequestDTO.getMedicationId());
        medicationBatch.setMedication(medication);
        if (medicationBatch.getQuantity() == null || medicationBatch.getQuantity() <= 0) {
            throw new BaseException(new ErrorMessage(MessageType.INVALID_INPUT,"Quantity must be greater than 0"));
        }
        medicationBatch = medicationBatchRepository.save(medicationBatch);
        return medicationBatchMapper.medicationBatchToResponseDTO(medicationBatch);
    }

    @Override
    public MedicationBatchResponseDTO updateMedicationBatch(Long id, MedicationBatchRequestDTO medicationBatchRequestDTO) {
        MedicationBatch medicationBatch = findMedicationBatchById(id);
        Medication medication = findMedicationById(medicationBatchRequestDTO.getMedicationId());
        medicationBatch.setMedication(medication);
        medicationBatchMapper.updateMedicationBatchFromRequestDTO(medicationBatchRequestDTO, medicationBatch);
        if (medicationBatch.getQuantity() == null || medicationBatch.getQuantity() <= 0) {
            throw new BaseException(new ErrorMessage(MessageType.INVALID_INPUT,"Quantity must be greater than 0"));
        }
        medicationBatch = medicationBatchRepository.save(medicationBatch);
        return medicationBatchMapper.medicationBatchToResponseDTO(medicationBatch);
    }

    @Override
    public void deleteMedicationBatch(Long id) {
        MedicationBatch medicationBatch = findMedicationBatchById(id);
        medicationBatchRepository.delete(medicationBatch);
        if(medicationBatchRepository.existsById(id)) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_DELETE_UNSUCCESS,id.toString()));
        }
    }
}
