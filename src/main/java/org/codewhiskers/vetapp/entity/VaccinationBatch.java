package org.codewhiskers.vetapp.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "vaccination_batches")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VaccinationBatch {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vaccination_id")
    private Vaccination vaccination;

    @ManyToOne
    @JoinColumn(name = "vaccination_inventory_id")
    private VaccinationInventory vaccinationInventory;

    private Long clinicId;
    private String batchNo;
    private LocalDate expiryDate;
    private Integer quantity;
    private LocalDate receivedDate;
}
