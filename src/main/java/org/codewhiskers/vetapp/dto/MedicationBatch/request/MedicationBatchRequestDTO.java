package org.codewhiskers.vetapp.dto.MedicationBatch.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MedicationBatchRequestDTO {

    // Eğer medicationId dışarıdan sağlanması gereken bir ID ise @NotNull kalmalı.
    // Aksi takdirde, bu satırı kaldırabiliriz.
    @NotNull(message = "Medication ID zorunludur.")
    private Long medicationId;

    @NotNull(message = "İlaç ID zorunludur.")
    private Long drugId;

    @NotNull(message = "Klinik ID zorunludur.")
    private Long clinicId;

    @NotNull(message = "Miktar zorunludur.")
    @Min(value = 1, message = "Miktar en az 1 olmalıdır.")
    private Integer quantity;

    @NotNull(message = "Son kullanma tarihi zorunludur.")
    @JsonFormat(pattern = "yyyy-MM-dd") // LocalDate için sadece tarih formatı yeterli
    private LocalDate expiryDate;

    @NotNull(message = "Alım tarihi zorunludur.")
    @JsonFormat(pattern = "yyyy-MM-dd") // LocalDate için sadece tarih formatı yeterli
    private LocalDate receivedDate;
}