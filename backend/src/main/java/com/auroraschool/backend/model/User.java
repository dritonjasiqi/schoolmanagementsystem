package com.auroraschool.backend.model;
/*
* Package provides the necessary annotations used to define the relational
* mapping between this Java Class and underlying ProgressSql Database via
* the Persistence Provider
*/
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import java.time.LocalDate;

/**
 * Abstract base entity that represent a system user
 * This class serves as foundation for all user types (Student, Professor, Admin) in the
 * application. It uses the {@link InheritanceType#JOINED} strategy to allow for flexible and efficient
 * storage of common user attributes while enabling specific fields for each user type in their respective tables.
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
abstract public class User {

    /**
     * Unique identifier for the user, generated automatically as a UUID.
     * This serves as the primary key for the user entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
        * Unique email address for the user, used for authentication and identification.
     */
    @Column(unique = true,nullable = false)
    private String email;

    /**
     * Encrypted password for the user authentication.
     * It uses a secure self-made hashing mechanism to
     * ensure that the password is stored securely in the database.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The full name of the user.
     */
    private String fullName;
    /**
     * Date of birth of the user, used for age verification and other age-related functionalities within the application.
     */
    private LocalDate dateOfBirth;
    /**
     * Profile picture URL for the user, allowing them to personalize their profile within the application.
     * This field is optional and can be null if the user has not set a profile picture.
     */
    private String profilePhotoUrl;

    /**
     * Verification status of the user account. This indicates of user can login or not.
     * Default value is {@code false}
     * Verification Process is done by email confirmation link valid for 7 days
     */
    private boolean isVerified = false;

    /**
     * Role of the user, stored as String
     * Indicate the thing a user can and cannot do.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles role;
}