package org.codewhiskers.vetapp.dto.Owner.response;

import lombok.Data;

@Data
public class OwnerResponseDTO {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String address;
}