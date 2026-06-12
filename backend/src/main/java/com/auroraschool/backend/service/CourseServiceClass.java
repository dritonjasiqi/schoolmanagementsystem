package com.auroraschool.backend.service;

import com.auroraschool.backend.model.Course;
import com.auroraschool.backend.model.Professor;
import com.auroraschool.backend.repository.CourseRepository;
import com.auroraschool.backend.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Concrete service implementation delivering business operations for managing {@link Course} lifecycle events.
 * <p>
 * This component handles domain validation rules, coordinates transactional updates between independent
 * data repositories, and orchestrates entity mappings. It is automatically registered as a managed
 * application bean within Spring's core application context. All public method executions run within a
 * localized database transaction boundary, enforcing structural data rollbacks if processing exceptions emerge.
 * </p>
 *
 * @author Driton Jasiqi
 * @see Service
 * @see Transactional
 * @see RequiredArgsConstructor
 * @see CourseService
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceClass implements CourseService {

    /**
     * Data access engine managing operations on the core persistence layer for courses.
     */
    private final CourseRepository courseRepository;

    /**
     * Data access engine executing profile verification and administrative checks on professor accounts.
     */
    private final ProfessorRepository professorRepository;

    /**
     * Instantiates a new course entity and establishes an operational link to a managing professor.
     * <p>
     * <b>Process Workflow:</b>
     * Evaluates the professor's identity token. If verified, the course object's structural relation field
     * is updated before flushing changes into persistent database storage. If validation fails, the
     * entire wrapping database transaction is rolled back.
     * </p>
     *
     * @param course      the unpersisted {@link Course} structural template sent by the client caller
     * @param professorId the unique {@link UUID} tracking the faculty member teaching this course
     * @return the fully saved and managed {@link Course} instance including database keys
     * @throws IllegalArgumentException if no professor entity can be located matching the provided identifier
     */
    @Override
    public Course createCourse(Course course, UUID professorId) throws IllegalArgumentException {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new IllegalArgumentException("Professor not found"));
        course.setProfessor(professor);
        return courseRepository.save(course);
    }

    /**
     * Locates a single course profile based on its unique relational database token.
     *
     * @param courseId the unique {@link UUID} key tracking the desired course record
     * @return the matching {@link Course} entity context located by the system lookup routines
     * @throws IllegalArgumentException if no persistent course entity matches the specified identifier
     */
    @Override
    public Course getCourse(UUID courseId) throws IllegalArgumentException {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
    }

    /**
     * Compiles an unpaginated summary list of every course currently stored within the database.
     *
     * @return a {@link List} of all registered {@link Course} entities, which may be empty if no rows are stored
     */
    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}