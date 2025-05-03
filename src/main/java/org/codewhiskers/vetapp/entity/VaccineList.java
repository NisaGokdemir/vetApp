package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vaccine_list")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VaccineList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vaccine_id", nullable = false)
    private Vaccination vaccination;

    @Column(name = "disease_target", nullable = false)
    private String diseaseTarget; // DiseaseTarget Entity yerine String olarak tutuluyor

    @ManyToOne
    @JoinColumn(name = "species_id", nullable = false)
    private Species species; // Varsayalım ki Species Entity'niz var (ÇOKAÇOK için ara tablo gerekebilir)

    @ManyToOne
    @JoinColumn(name = "vaccine_type_id", nullable = false)
    private VaccineType vaccineType; // Varsayalım ki VaccineType Entity'niz var

    private String notes;
}