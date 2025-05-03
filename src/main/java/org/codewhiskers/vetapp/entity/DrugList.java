package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "drug_list")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DrugList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "drug_id", nullable = false)
    private Drug drug;

    @Column(nullable = false)
    private String activeSubstance;

    @ManyToOne
    @JoinColumn(name = "usage_area_id", nullable = false)
    private UsageArea usageArea;

    @ManyToOne
    @JoinColumn(name = "species_id", nullable = false)
    private Species species; // Varsayalım ki Species Entity'niz var

    @Column(nullable = false)
    private String administrationRoute;
}