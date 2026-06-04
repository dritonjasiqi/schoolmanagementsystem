package com.auroraschool.backend.repostiory;

import com.auroraschool.backend.model.Professor;
// Provides standard JPA CRUD operations and query method support.
import org.springframework.data.jpa.repository.JpaRepository;
// Marks this component for Spring's component scanning and
// enables exception translation
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for Professor Entity, extending JpaRepository for CRUD Operation and custom query methods.
 * It includes methods to check if a professor exists by email.
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor,UUID> {
    /**
     * Give the Professor associated with this Email if he exists.
     * @param email Personal Email of the Professor
     * @return Professor or null
     */
    Optional<Professor> findByEmail(String email);
}
