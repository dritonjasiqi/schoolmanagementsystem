package com.auroraschool.backend.repository;

import com.auroraschool.backend.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Data access repository interface for executing persistence operations on {@link Professor} entities.
 * <p>
 * This interface extends {@link JpaRepository}, automatically exposing standard CRUD capabilities,
 * pagination support, and sorting operations backed by Spring Data JPA. It provides customized evaluation
 * and retrieval methods using unique profile attributes like emails.
 * </p>
 *
 * @author Driton Jasiqi
 * @see JpaRepository
 * @see Repository
 * @see Professor
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, UUID> {

    /**
     * Retrieves a professor profile matching the designated email address.
     * <p>
     * This query evaluates the core identity records to locate matching faculty entities
     * during login, authentication checkpoints, or cross-system profile validations.
     * </p>
     *
     * @param email the unique email identity string tracking the professor
     * @return an {@link Optional} containing the located {@link Professor} entity, or {@link Optional#empty()}
     * if no matching record exists in the database
     */
    Optional<Professor> findByEmail(String email);
}