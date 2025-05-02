package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "prescriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosis;

    @CurrentTimestamp
    private LocalDate createdAt;

    @Column(columnDefinition = "TEXT")
    private String notes;
}