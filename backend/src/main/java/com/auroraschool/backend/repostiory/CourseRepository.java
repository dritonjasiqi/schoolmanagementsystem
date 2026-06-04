package com.auroraschool.backend.repostiory;

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
 * Repository interface for Course entity, extending JpaRepository to provide CRUD operations and custom query methods.
 * It includes a method to find courses by their associated professor.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    /**
     * Finds a list of courses taught by a specific professor.
     * @param professor The professor whose courses are to be retrieved.
     * @return List of courses associated with the given professor.
     */
    List<Course> findByProfessor(Professor professor);
}
