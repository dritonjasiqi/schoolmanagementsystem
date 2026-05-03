package com.auroraschool.backend.model;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.UUID;


import java.time.LocalDate;

abstract public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;
    private LocalDate dateOfBirth;
    private String urlOfProfilePhoto;
    private boolean isVerified = false;

    @Enumerated(EnumType.STRING)
    private Roles role;
}