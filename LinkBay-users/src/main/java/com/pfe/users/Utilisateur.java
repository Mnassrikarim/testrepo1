package com.pfe.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false, length = 60) // BCrypt hash length
    private String password;

    @Column(nullable = false)
    private String numTel;

    @Column(nullable = false)
    private String adresse;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum role;
}