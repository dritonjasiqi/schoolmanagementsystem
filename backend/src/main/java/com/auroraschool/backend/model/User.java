package com.auroraschool.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Abstract base database entity representing a structural User within the Aurora School system.
 * <p>
 * This class serves as the foundational root for all localized user sub-types (such as {@code Student},
 * {@code Professor}, and {@code Admin}). It leverages the {@link InheritanceType#JOINED} mapping strategy
 * to organize common security and profile data within a single core table, while cleanly segregating
 * specialized sub-type attributes into their own distinct tables via primary key references.
 * </p>
 *
 * @author Driton Jasiqi
 * @see Entity
 * @see Table
 * @see Inheritance
 * @see Roles
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class User {

    /**
     * Unique operational token representing the user instance, automatically generated as a {@link UUID}.
     * This field serves as the primary key within the central user management table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The unique identity email address assigned to the account.
     * <p>
     * Utilized as the primary identifier during authentication handshakes. Enforces both
     * {@code unique = true} and {@code nullable = false} constraints at the relational schema layer.
     * </p>
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * The encrypted credential string utilized during login verification routines.
     * <p>
     * Stored strictly in an obscured state utilizing a secure cryptographic hashing mechanism
     * to protect user data. This column is mandatory and cannot be null.
     * </p>
     */
    @Column(nullable = false)
    private String password;

    /**
     * The full name (given name and surname) associated with the user account profile.
     */
    private String fullName;

    /**
     * The legal date of birth of the user, evaluated internally for age verification workflows
     * and age-restricted application functionalities.
     */
    private LocalDate dateOfBirth;

    /**
     * A remote resource URL pointing to the user's uploaded avatar or profile image.
     * <p>
     * This parameter is optional and defaults to {@code null} if the account holder has not
     * personalized their system visualization assets.
     * </p>
     */
    private String profilePhotoUrl;

    /**
     * Administrative status flag tracking whether the user account is validated and active.
     * <p>
     * Accounts default to a locked or unverified state ({@code false}) upon registration.
     * Activation is updated following a successful email verification process via a token link
     * valid for a 7-day processing window.
     * </p>
     */
    private boolean isVerified = false;

    /**
     * The main application security access tier role assigned to the user.
     * <p>
     * Persisted inside the relational table using {@link EnumType#STRING} to map logical
     * permissions and structural access tokens securely. This column cannot be null.
     * </p>
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles role;
}