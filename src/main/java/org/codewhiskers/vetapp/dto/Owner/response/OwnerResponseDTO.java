package org.codewhiskers.vetapp.dto.Owner.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.codewhiskers.vetapp.dto.User.response.UserResponseDTO;

@Data
public class OwnerResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String debt;
    private String notes;
}