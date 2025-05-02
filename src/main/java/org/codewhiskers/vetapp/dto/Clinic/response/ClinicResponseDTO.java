package org.codewhiskers.vetapp.dto.Clinic.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicResponseDTO {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
}
