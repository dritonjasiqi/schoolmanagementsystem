package com.auroraschool.backend.repository;

import com.auroraschool.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Data access repository interface for executing persistence operations on {@link User} entities.
 * <p>
 * This interface extends {@link JpaRepository}, automatically exposing standard CRUD capabilities,
 * pagination support, and sorting operations backed by Spring Data JPA. As the repository for the
 * base class of the inheritance hierarchy, it can be used to query across all sub-types (Students,
 * Professors, and Admins) using common identity criteria like email.
 * </p>
 *
 * @author Driton Jasiqi
 * @see JpaRepository
 * @see Repository
 * @see User
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Retrieves a user profile matching the designated email address.
     * <p>
     * This query evaluates core security identifiers to locate accounts across the system
     * during login, authentication challenges, or token verification handshakes.
     * </p>
     *
     * @param email the unique email address tracking the user account
     * @return an {@link Optional} containing the located {@link User} entity, or {@link Optional#empty()}
     * if no matching record exists in the database
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks whether an active user account exists with the designated email address.
     * <p>
     * Highly optimized check utilized during registration processing workflows to enforce
     * unique constraint requirements and prevent duplicate registration attempts before saving entries.
     * </p>
     *
     * @param email the email address string to verify
     * @return {@code true} if a user matching the email parameter already exists; {@code false} otherwise
     */
    boolean existsByEmail(String email);
}