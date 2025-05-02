package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "diagnoses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String diagnosis;

    private String symptoms;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(columnDefinition = "TEXT")
    private String treatmentPlan;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private User vet;

    @CurrentTimestamp
    private LocalDate diagnosisDate;

    private LocalDateTime nextFollowUpDate;
}
