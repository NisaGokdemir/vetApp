package org.codewhiskers.vetapp.dto.PatientAllergy.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientAllergyRequestDTO {
    
    @NotNull(message = "Hasta ID'si gereklidir")
    private Long patientId;
    
    @NotBlank(message = "Alerji adı gereklidir")
    private String allergy;
    
    private String severity;
    
    private String note;
}
