package org.codewhiskers.vetapp.dto.User.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Specialization.response.SpecializationResponseDTO;

@Getter
@Setter

public class UserResponseDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private SpecializationResponseDTO specialization;
}
