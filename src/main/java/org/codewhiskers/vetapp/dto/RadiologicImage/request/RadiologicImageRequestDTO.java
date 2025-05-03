package org.codewhiskers.vetapp.dto.RadiologicImage.request;

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
public class RadiologicImageRequestDTO {
    private String testName;
    private Long clinicId;
    private Long patientId;
}
