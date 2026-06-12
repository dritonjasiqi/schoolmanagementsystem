package com.auroraschool.backend.repository;

import com.auroraschool.backend.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Data access repository interface for executing persistence operations on {@link Enrollment} entities.
 * <p>
 * This interface extends {@link JpaRepository}, automatically exposing standard CRUD capabilities,
 * pagination support, and sorting operations backed by Spring Data JPA. It provides customized evaluation
 * and retrieval methods using the relational compound keys of students and courses.
 * </p>
 *
 * @author Driton Jasiqi
 * @see JpaRepository
 * @see Repository
 * @see Enrollment
 */
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {

    /**
     * Checks whether an active enrollment record exists linking a specific student to a specific course.
     * <p>
     * This derived existential query matches the underlying relationship foreign key constraints
     * to quickly verify participation states without loading full entity objects into memory.
     * </p>
     *
     * @param studentId the {@link UUID} identity tracking the student
     * @param courseId  the {@link UUID} identity tracking the course
     * @return {@code true} if a matching registration row is located in the database; {@code false} otherwise
     */
    boolean existsByStudentIdAndCourseId(UUID studentId, UUID courseId);

    /**
     * Retrieves a specific enrollment record mapping the intersection of a designated student and course.
     *
     * @param studentId the {@link UUID} identity tracking the student
     * @param courseId  the {@link UUID} identity tracking the course
     * @return an {@link Optional} containing the located {@link Enrollment} entity, or {@link Optional#empty()}
     * if no matching registration row exists
     */
    Optional<Enrollment> findByStudentIdAndCourseId(UUID studentId, UUID courseId);

    /**
     * Retrieves all enrollment records registered to a single student profile.
     * <p>
     * This lookup is highly helpful for aggregating academic transcripts or active timetables
     * belonging to a specific student context.
     * </p>
     *
     * @param studentId the {@link UUID} identity tracking the student whose registrations are being pulled
     * @return a {@link List} of {@link Enrollment} entities associated with the given student,
     * which may be empty if no registrations are found
     */
    List<Enrollment> findByStudentId(UUID studentId);
}