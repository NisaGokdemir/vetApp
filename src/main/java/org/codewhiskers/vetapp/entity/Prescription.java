package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

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
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TEXT")
    private String notes;
}