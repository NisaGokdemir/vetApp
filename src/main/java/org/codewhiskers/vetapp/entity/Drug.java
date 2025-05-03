package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "drugs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Drug {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}