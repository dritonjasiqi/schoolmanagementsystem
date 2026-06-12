package com.auroraschool.backend.repository;

import com.auroraschool.backend.model.Course;
import com.auroraschool.backend.model.Professor;
// Provides standard JPA CRUD operations and query method support.
import org.springframework.data.jpa.repository.JpaRepository;
// Marks this component for Spring's component scanning and
// enables exception translation
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Data access repository interface for executing persistence operations on {@link Course} entities.
 * <p>
 * This interface extends {@link JpaRepository}, automatically exposing standard CRUD capabilities,
 * pagination support, and sorting operations backed by Spring Data JPA. It includes derived query methods
 * to handle specific relational lookups within the underlying database.
 * </p>
 *
 * @author Driton Jasiqi
 * @see JpaRepository
 * @see Repository
 * @see Course
 * @see Professor
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

    /**
     * Retrieves all educational courses taught or managed by a designated professor profile.
     * <p>
     * This is a derived query method processed automatically by the Spring Data framework at runtime,
     * evaluating the many-to-one mapping relationship established within the database schema.
     * </p>
     *
     * @param professor the concrete {@link Professor} instance whose courses are being queried
     * @return a {@link List} of {@link Course} entities associated with the given faculty member,
     * which may be empty if no matching courses are found
     */
    List<Course> findByProfessor(Professor professor);
}