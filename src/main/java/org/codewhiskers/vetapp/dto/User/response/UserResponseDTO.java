package org.codewhiskers.vetapp.dto.User.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Clinic.response.ClinicResponseDTO;
import org.codewhiskers.vetapp.dto.Specialization.response.SpecializationResponseDTO;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String username;
    private ClinicResponseDTO clinic;

}
