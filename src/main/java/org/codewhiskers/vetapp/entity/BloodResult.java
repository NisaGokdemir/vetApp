package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "blood_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String testName;

    @CurrentTimestamp
    private LocalDateTime uploadDate;

    private String fileName;
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
