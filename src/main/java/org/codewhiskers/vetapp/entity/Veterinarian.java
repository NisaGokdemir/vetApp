package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "veterinarians")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Veterinarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    @Column(columnDefinition = "TEXT")
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private Specialization specialization;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;
}
