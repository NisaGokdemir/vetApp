package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.PrescriptionItem.request.PrescriptionItemRequestDTO;
import org.codewhiskers.vetapp.dto.PrescriptionItem.response.PrescriptionItemResponseDTO;
import org.codewhiskers.vetapp.entity.Medication;
import org.codewhiskers.vetapp.entity.MedicationBatch;
import org.codewhiskers.vetapp.entity.Prescription;
import org.codewhiskers.vetapp.entity.PrescriptionItem;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.PrescriptionItemMapper;
import org.codewhiskers.vetapp.repository.MedicationBatchRepository;
import org.codewhiskers.vetapp.repository.MedicationRepository;
import org.codewhiskers.vetapp.repository.PrescriptionItemRepository;
import org.codewhiskers.vetapp.repository.PrescriptionRepository;
import org.codewhiskers.vetapp.service.IPrescriptionItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class PrescriptionItemServiceImpl implements IPrescriptionItemService {

    private final PrescriptionItemRepository prescriptionItemRepository;
    private final PrescriptionItemMapper prescriptionItemMapper;
    private final PrescriptionRepository prescriptionRepository;
    private final MedicationRepository medicationRepository;
    private final MedicationBatchRepository medicationBatchRepository;

    private PrescriptionItem findPrescriptionItemById(Long id) {
        return prescriptionItemRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "PrescriptionItem ID: " + id))
                );
    }

    private Medication findMedicationById(Long id) {
        return medicationRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Medication ID: " + id))
                );
    }

    private Prescription findPrescriptionById(Long id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Prescription ID: " + id))
                );
    }

    @Override
    public Page<PrescriptionItemResponseDTO> getAllPrescriptionItems(Pageable pageable) {
        Page<PrescriptionItem> page = prescriptionItemRepository.findAll(pageable);
        if (page.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORDS_NOT_FOUND, ""));
        }
        return page.map(prescriptionItemMapper::toPrescriptionItemResponseDto);
    }

    @Override
    public PrescriptionItemResponseDTO getPrescriptionItemById(Long id) {
        PrescriptionItem prescriptionItem = findPrescriptionItemById(id);
        return prescriptionItemMapper.toPrescriptionItemResponseDto(prescriptionItem);
    }

    @Override
    public PrescriptionItemResponseDTO createPrescriptionItem(PrescriptionItemRequestDTO dto) {
        PrescriptionItem prescriptionItem = prescriptionItemMapper.toPrescriptionItem(dto);

        if (dto.getMedicationId() != null && dto.getPrescriptionId() != null) {
            Medication medication = findMedicationById(dto.getMedicationId());
            prescriptionItem.setMedication(medication);

            Prescription prescription = findPrescriptionById(dto.getPrescriptionId());
            prescriptionItem.setPrescription(prescription);
        }
        
        if (dto.getMedicationBatchId() != null) {
            MedicationBatch batch = medicationBatchRepository.findById(dto.getMedicationBatchId())
                    .orElseThrow(() -> new BaseException(
                            new ErrorMessage(MessageType.NO_RECORD_EXIST, "Medication Batch ID: " + dto.getMedicationBatchId())));

            int dailyDose = Integer.parseInt(dto.getDailyDose());
            int totalNeeded = dailyDose * dto.getDurationDays();
            prescriptionItem.setTotalAmount(totalNeeded);

            if (batch.getQuantity() < totalNeeded) {
                throw new BaseException(new ErrorMessage(MessageType.RECORD_CREATE_UNSUCCESS,
                        "Seçilen batch'te yeterli stok yok. Mevcut: " + batch.getQuantity() + ", Gerekli: " + totalNeeded));
            }

            // Stoğu düş
            batch.setQuantity(batch.getQuantity() - totalNeeded);
            medicationBatchRepository.save(batch);

        }

        prescriptionItemRepository.save(prescriptionItem);

        if (prescriptionItem.getId() == null) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_CREATE_UNSUCCESS,
                    "Kayıt başarısız"));
        }

        return prescriptionItemMapper.toPrescriptionItemResponseDto(prescriptionItem);
    }


    @Override
    public PrescriptionItemResponseDTO updatePrescriptionItem(Long id, PrescriptionItemRequestDTO dto) {
        PrescriptionItem prescriptionItem = findPrescriptionItemById(id);
        if (dto.getMedicationId() != null && dto.getPrescriptionId() != null) {
            Medication medication = findMedicationById(dto.getMedicationId());
            prescriptionItem.setMedication(medication);
            Prescription prescription = findPrescriptionById(dto.getPrescriptionId());
            prescriptionItem.setPrescription(prescription);
        }
        prescriptionItemRepository.save(prescriptionItem);
        if (prescriptionItem.getId() == null) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_CREATE_UNSUCCESS, prescriptionItem.getId().toString()));
        }
        return prescriptionItemMapper.toPrescriptionItemResponseDto(prescriptionItem);
    }

    @Override
    public void deletePrescriptionItem(Long id) {
        PrescriptionItem prescriptionItem = findPrescriptionItemById(id);
        prescriptionItemRepository.delete(prescriptionItem);
        if (prescriptionItemRepository.existsById(id)) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_DELETE_UNSUCCESS, prescriptionItem.getId().toString()));
        }
    }
}
