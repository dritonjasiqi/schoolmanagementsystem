package com.auroraschool.backend.service;

import com.auroraschool.backend.model.Course;
import com.auroraschool.backend.model.Professor;
import com.auroraschool.backend.repostiory.CourseRepository;
import com.auroraschool.backend.repostiory.ProfessorRepository;

// It wraps Methods execution into a Database transaction, if an Exception thrown rollback
// and if not than the Transaction is commited
import jakarta.transaction.Transactional;
// Generates a constructor for all final fields, enforcing clean constructor injection.
import lombok.RequiredArgsConstructor;
// Package that tell Spring this class is a Service containing Business Logic
// and it will be automatically detected and registered as a Bean in the Spring context
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service for Course entity with business operations to create, retrieve, and list courses.
 * Associates courses with professors and executes repository operations within
 * transactional boundaries to ensure consistency.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceClass implements CourseService {
    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;

    /**
     * Creates a Course and associate it to a professor
     * @param course Course Object
     * @param professorId Id of the professor to associate the course to
     * @return Course
     * @throws IllegalArgumentException Professor with professorId doesnt exist
     */
    @Override
    public Course createCourse(Course course, UUID professorId) throws IllegalArgumentException {
        Professor professor = professorRepository.findById(professorId).orElseThrow(() -> new IllegalArgumentException("Professor not found"));
        course.setProfessor(professor);
        return courseRepository.save(course);
    }

    /**
     * Gives the Course associated with courseId
     * @param courseId Id of the course to search
     * @return Course with courseId
     * @throws IllegalArgumentException Course with CourseId doesnt exist
     */
    @Override
    public Course getCourse(UUID courseId)  throws IllegalArgumentException{
        return courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Course not found"));
    }

    /**
     * @return Gives the List of all Courses saved into the Database
     */
    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
