package com.auroraschool.backend.repository;

import com.auroraschool.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Data access repository interface for executing persistence operations on {@link Student} entities.
 * <p>
 * This interface extends {@link JpaRepository}, automatically exposing standard CRUD capabilities,
 * pagination support, and sorting operations backed by Spring Data JPA. It provides customized evaluation
 * and retrieval methods using unique student identity numbers.
 * </p>
 *
 * @author Driton Jasiqi
 * @see JpaRepository
 * @see Repository
 * @see Student
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    /**
     * Retrieves a student profile matching the designated unique enrollment number.
     * <p>
     * This query evaluates administrative identity credentials to locate a matching student
     * record during course registration lookups or academic verification workflows.
     * </p>
     *
     * @param enrollmentNumber the unique academic enrollment number tracking the student
     * @return an {@link Optional} containing the located {@link Student} entity, or {@link Optional#empty()}
     * if no student record matches the provided enrollment number
     */
    Optional<Student> findByEnrollmentNumber(Long enrollmentNumber);
}