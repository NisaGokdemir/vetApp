package org.codewhiskers.vetapp.dto.Veterinarian.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeterinarianRequestDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private Long userId;
    private Long specializationId;
    private Long clinicId;
}
