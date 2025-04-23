package org.codewhiskers.vetapp.dto.PrescriptionItem.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Medication.response.MedicationResponseDTO;
import org.codewhiskers.vetapp.dto.Prescription.response.PrescriptionResponseDTO;

@Getter
@Setter
public class PrescriptionItemResponseDTO {
    private Long id;
    private PrescriptionResponseDTO prescription;
    private MedicationResponseDTO medicationId;
    private String dailyDose;
    private Integer durationDays;
    private Integer totalAmount;

}
