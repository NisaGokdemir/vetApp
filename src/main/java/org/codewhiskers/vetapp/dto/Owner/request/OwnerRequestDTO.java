package org.codewhiskers.vetapp.dto.Owner.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OwnerRequestDTO {

    @NotBlank(message = "Ad Soyad boş olamaz.")
    @Size(min = 5, max = 100, message = "Ad Soyad 5 ile 100 karakter arasında olmalıdır.")
    private String fullName;

    @NotBlank(message = "Telefon numarası boş olamaz.")
    @Pattern(regexp = "^(05)([0-9]{9})$", message = "Geçerli bir Türkiye telefon numarası formatında olmalıdır (örneğin: 05xxxxxxxxx).")
    private String phone;

    @Email(message = "Geçerli bir e-posta adresi formatında olmalıdır.")
    private String email;

    @Size(max = 255, message = "Adres en fazla 255 karakter olabilir.")
    private String address;
}