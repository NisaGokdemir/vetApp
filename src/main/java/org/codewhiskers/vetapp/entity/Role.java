package org.codewhiskers.vetapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.codewhiskers.vetapp.enums.RoleType;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType name;
}
