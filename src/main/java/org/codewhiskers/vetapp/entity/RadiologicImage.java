package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "radiologic_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RadiologicImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String testName;
    private String fileName;
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @CurrentTimestamp
    private LocalDate uploadDate;
}
