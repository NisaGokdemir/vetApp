package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "blood_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    @ManyToOne
    @JoinColumn(name = "species_id")
    private Species species;
}
