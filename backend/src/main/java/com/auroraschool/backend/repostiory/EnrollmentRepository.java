package com.auroraschool.backend.repostiory;

import com.auroraschool.backend.model.Course;
import com.auroraschool.backend.model.Enrollment;
import com.auroraschool.backend.model.Student;
// Provides standard JPA CRUD operations and query method support.
import org.springframework.data.jpa.repository.JpaRepository;
// Marks this component for Spring's component scanning and
// enables exception translation
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for Enrollment Entity, extending JpaRepository for CRUD Operation and custom query methods.
 * It includes methods to check if a student is enrolled in a course, find enrollment by student Id  and course Id.
 */
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
    /**
     * Stores Value of a Enrollment exists between Student and Course
     */
    boolean existsByStudentIdAndCourseId(UUID studentId,UUID courseId);

    /**
     * Take StudentId and CourseId and return Enrollment if exists
     * @param studentId Id of a Student
     * @param courseId Id of a course
     * @return Enrollment or null
     */
    Optional<Enrollment> findByStudentIdAndCourseId(UUID studentId, UUID courseId);

    /**
     * Takes a StudentId and returns all the Enrollment for this student
     * @param studentId StudentId
     * @return a List of Enrollments
     */
    List<Enrollment> findByStudentId(UUID studentId);
}
