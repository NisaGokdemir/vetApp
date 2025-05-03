package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vaccination_protocols")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VaccinationProtocol {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vaccination_id")
    private Vaccination vaccination;

    private String brand; // Aşı markası (örn: PfizerVet)

    @ManyToOne
    @JoinColumn(name = "species_id")
    private Species species;

    private Integer firstDoseWeek; // Örn: 6. hafta
    private Integer boosterInterval; // Kaç hafta sonra tekrar
    private Boolean isWeightBased; // Doz ağırlığa göre mi
    private Double dosePerKg; // mg/kg cinsinden
    private Double maxSingleDose; // Maksimum doz (örn: 2.5 mL)
}