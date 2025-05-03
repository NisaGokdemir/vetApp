package org.codewhiskers.vetapp.dto.Drug.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrugResponseDTO {
    private Long id;
    private String name;
}