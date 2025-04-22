package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.dto.Medication.request.MedicationRequestDTO;
import org.codewhiskers.vetapp.dto.Medication.response.MedicationResponseDTO;
import org.springframework.data.domain.Page;

public interface IMedicationService {

    Page<MedicationResponseDTO> getAllMedications(int page,int size);

    MedicationResponseDTO getMedicationById(Long id);

    void DeleteMedicationById(Long id);

    MedicationResponseDTO createMedication(MedicationRequestDTO medication);

    MedicationResponseDTO updateMedication(Long id, MedicationRequestDTO medication);

}
