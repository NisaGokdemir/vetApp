package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private LocalDate dateOfBirth;
    private String sex;
    private Double weight;
    private String chipNumber;

    @ManyToOne
    @JoinColumn(name = "blood_type_id")
    private BloodType bloodType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "species_id")
    private Species species;

    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

}
