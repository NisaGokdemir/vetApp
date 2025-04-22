package org.codewhiskers.vetapp.dto.MedicationBatch.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MedicationBatchRequestDTO {

    @NotNull(message = "medicationId date is required")
    private Long medicationId;

    @NotNull(message = "quantity date is required")
    private Integer quantity;

    @NotNull(message = "Expiry date is required")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime expiryDate;

    @NotNull(message = "received date is required")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime receivedDate;
}
