package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "vaccine_protocols")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VaccineProtocols {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vaccination_id", nullable = false)
    private Vaccination vaccination;

    private String brand;

    @ManyToOne
    @JoinColumn(name = "species_id", nullable = false)
    private Species species;

    private Integer firstDoseWeek;

    private Integer boosterInterval;

    private Boolean isWeightBased;

    private Double dosePerKg;

    private Double maxSingleDose;
}