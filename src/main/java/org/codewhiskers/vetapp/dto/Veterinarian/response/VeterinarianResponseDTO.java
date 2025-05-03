package org.codewhiskers.vetapp.dto.Veterinarian.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Clinic.response.ClinicResponseDTO;
import org.codewhiskers.vetapp.dto.Specialization.response.SpecializationResponseDTO;
import org.codewhiskers.vetapp.dto.User.response.UserResponseDTO;

@Getter
@Setter
public class VeterinarianResponseDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private UserResponseDTO user;
    private SpecializationResponseDTO specialization;
    private ClinicResponseDTO clinic;
}
