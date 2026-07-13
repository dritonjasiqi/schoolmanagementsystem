package com.auroraschool.backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.UUID;
import java.time.LocalDateTime;

/**
 * Represents a verification token used for user account verification,
 * password resets, or other email-based validation workflows.
 *
 * <p>Each token is associated with a specific {@link User}, has a unique
 * string value, and is configured to expire after a set duration (default is 24 hours).</p>
 *
 * <p>This class is mapped as a JPA entity to the {@code verification_tokens} database table.
 * Getter, setter, and no-argument constructor methods are generated at runtime via Lombok.</p>
 *
 * @author Driton Jasiqi
 * @see User
 */
@Entity
@Table(name = "verification_tokens")
@Getter
@Setter
@NoArgsConstructor
public class VerificationToken {

    /**
     * The default lifespan of a verification token in hours.
     */
    private static final int EXPIRATION_HOURS = 24;

    /**
     * The unique identifier for the verification token record in the database.
     * Automatically generated using a UUID strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The unique token string sent to the user.
     * Generated as a random UUID string by default.
     */
    @Column(nullable = false, unique = true)
    private String token;

    /**
     * The date and time when this token becomes invalid.
     */
    @Column(nullable = false)
    private LocalDateTime expiryDate;

    /**
     * The user associated with this verification token.
     * Mapped as a eager-loaded, one-to-one relationship with the {@code user_id} foreign key.
     */
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    /**
     * Constructs a new {@code VerificationToken} for a specified user.
     *
     * <p>This constructor initializes the token with a randomly generated UUID string
     * and sets the expiration timestamp to 24 hours from the current system time.</p>
     *
     * @param user the {@link User} to link to this verification token
     */
    public VerificationToken(User user) {
        this.user = user;
        this.token = UUID.randomUUID().toString();
        this.expiryDate = LocalDateTime.now().plusHours(EXPIRATION_HOURS);
    }

    /**
     * Checks whether the token has passed its expiration date.
     *
     * @return {@code true} if the current system time is strictly after the {@code expiryDate};
     *         {@code false} otherwise
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }
}