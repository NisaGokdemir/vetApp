package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.dto.Medication.request.MedicationRequestDTO;
import org.codewhiskers.vetapp.dto.Medication.response.MedicationResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IRestMedicationController {

    ResponseEntity<Page<MedicationResponseDTO>> getAllMedications(int page, int size);

    ResponseEntity<MedicationResponseDTO> getMedicationById(Long id);

    ResponseEntity<Void> DeleteMedicationById(Long id);

    ResponseEntity<MedicationResponseDTO> createMedication(MedicationRequestDTO medication);

    ResponseEntity<MedicationResponseDTO> updateMedication(Long id, MedicationRequestDTO medication);
}
