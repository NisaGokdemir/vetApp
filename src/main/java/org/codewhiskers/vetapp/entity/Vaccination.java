package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vaccinations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Vaccination {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String vaccinationUnit; // Örn: mL veya doz
}