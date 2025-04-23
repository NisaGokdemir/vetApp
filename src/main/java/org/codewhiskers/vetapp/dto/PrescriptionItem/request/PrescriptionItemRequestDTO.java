package org.codewhiskers.vetapp.dto.PrescriptionItem.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrescriptionItemRequestDTO {

    private Long prescriptionId;
    private Long medicationId;
    private Long medicationBatchId;
    private String dailyDose;
    private Integer durationDays;
    private Integer totalAmount;

}
