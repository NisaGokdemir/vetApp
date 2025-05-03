package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vaccination_inventory")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VaccinationInventory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vaccination_id")
    private Vaccination vaccination;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    private Integer stockWarningLevel;
}