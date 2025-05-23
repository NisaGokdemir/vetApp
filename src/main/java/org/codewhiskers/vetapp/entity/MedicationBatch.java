package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "medication_batches")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MedicationBatch {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    private String batchNo;
    private LocalDate expiryDate;
    private Integer quantity;
    private LocalDate receivedDate;
}
