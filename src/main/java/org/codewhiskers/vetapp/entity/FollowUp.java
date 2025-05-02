package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "follow_ups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String frequency;
    private LocalDate nextFollowUpDate;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosis;
}
