package com.auroraschool.backend.repostiory;

import com.auroraschool.backend.model.Student;
// Provides standard JPA CRUD operations and query method support.
import org.springframework.data.jpa.repository.JpaRepository;
// Marks this component for Spring's component scanning and
// enables exception translation
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for Student Entity, extending JpaRepository for CRUD Operation and custom query methods.
 * It includes methods to check if a student exists by enrollment number.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    /**
     * Checks if a student exists by their enrollment number.
     * @param enrollmentNumber Number of Enrollment of a student
     * @return Student or Null
     */
    Optional<Student> findByEnrollmentNumber(Long enrollmentNumber);
}
