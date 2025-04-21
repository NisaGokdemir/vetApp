package org.codewhiskers.vetapp.dto.Patient.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class PatientRequestDTO {

    @NotBlank(message = "Hasta adı boş olamaz.")
    @Size(min = 2, max = 100, message = "Hasta adı 2 ile 100 karakter arasında olmalıdır.")
    private String name;

    @NotNull(message = "Hasta yaşı boş olamaz.")
    @Min(value = 0, message = "Yaş 0'dan küçük olamaz.")
    private Integer age;

    @NotNull(message = "Hasta ağırlığı boş olamaz.")
    @Positive(message = "Ağırlık pozitif bir değer olmalıdır.")
    private Double weight;

    @NotBlank(message = "Çip numarası boş olamaz.")
    @Size(min = 10, max = 20, message = "Çip numarası 10 ile 20 karakter arasında olmalıdır.")
    private String chipNumber;

    @NotNull(message = "Kan tipi ID'si boş olamaz.")
    private Long bloodTypeId;

    @NotNull(message = "Sahip ID'si boş olamaz.")
    private Long ownerId;

    @NotNull(message = "Tür ID'si boş olamaz.")
    private Long speciesId;

    @NotNull(message = "Irk ID'si boş olamaz.")
    private Long breedId;

    @Future(message = "Bir sonraki kontrol tarihi gelecekte bir tarih olmalıdır.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime nextCheckup;

    // @NotNull(message = "Takip gerekli mi alanı boş olamaz.")
    // private Boolean followUpRequired;
}