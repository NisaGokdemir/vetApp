package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "drug_protocols")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DrugProtocol {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;

    @ManyToOne
    @JoinColumn(name = "species_id")
    private Species species;

    private Integer minAge; // ay cinsinden minimum yaş
    private Double maxDoseMgKg; // Örn: 10 mg/kg
    private String frequency; // Örn: Günde 2 kez
    private String notes;
}