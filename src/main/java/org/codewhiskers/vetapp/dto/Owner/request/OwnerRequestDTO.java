package org.codewhiskers.vetapp.dto.Owner.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OwnerRequestDTO {

    @NotBlank(message = "Ad boş olamaz.")
    private String firstName;

    @NotBlank(message = "Soyad boş olamaz.")
    private String lastName;

    @NotBlank(message = "Telefon numarası boş olamaz.")
    @Pattern(regexp = "^(05)([0-9]{9})$", message = "Geçerli bir Türkiye telefon numarası formatında olmalıdır (örneğin: 05xxxxxxxxx).")
    private String phone;

    @Email(message = "Geçerli bir e-posta adresi formatında olmalıdır.")
    private String email;

    @Size(max = 255, message = "Adres en fazla 255 karakter olabilir.")
    private String address;

    private String debt;

    private String notes;

    private Long userId;

}