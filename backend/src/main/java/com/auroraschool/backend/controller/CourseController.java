package com.auroraschool.backend.controller;

import com.auroraschool.backend.model.Course;
import com.auroraschool.backend.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller exposing Course-related endpoints under {@code /api/courses}.
 * <p>
 * This class provides operations to list courses, retrieve individual courses by their unique
 * identifier, and register new courses under an existing professor. Access controls are applied
 * to administrative operations using method-level security expressions.
 * </p>
 *
 * @author Driton Jasiqi
 * @see RestController
 * @see RequestMapping
 * @see CourseService
 */
@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    /**
     * Service layer dependency executing core business rules for course workflows.
     */
    private final CourseService courseService;

    /**
     * Retrieves all courses currently registered within the system.
     * <p>
     * Expected HTTP status outcomes:
     * <ul>
     * <li><b>200 OK:</b> Execution successful; returns a list of courses (which may be empty).</li>
     * </ul>
     * </p>
     *
     * @return a {@link ResponseEntity} wrapping the list of available {@link Course} objects
     */
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    /**
     * Retrieves a specific course by its unique identity token.
     * <p>
     * Expected HTTP status outcomes:
     * <ul>
     * <li><b>200 OK:</b> The course was located successfully; returns the matching payload.</li>
     * <li><b>404 Not Found:</b> No persistent course entity exists matching the provided UUID.</li>
     * </ul>
     * </p>
     *
     * @param id the {@link UUID} tracking the desired course
     * @return a {@link ResponseEntity} containing the matching {@link Course} entity on success, or an empty 404 block
     */
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(courseService.getCourse(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Creates a new course entity and links it directly to an existing professor profile.
     * <p>
     * <b>Security Restriction:</b> This method enforces role-based access controls via
     * {@link PreAuthorize} and is restricted exclusively to administrative operations
     * or professor accounts.
     * </p>
     * <p>
     * Expected HTTP status outcomes:
     * <ul>
     * <li><b>201 Created:</b> The validation passed and the entity was saved; returns the completed payload.</li>
     * <li><b>404 Not Found:</b> The designated professor's UUID could not be linked to an active account.</li>
     * <li><b>500 Internal Server Error:</b> An unexpected database or system exception occurred.</li>
     * </ul>
     * </p>
     *
     * @param course      the {@link Course} template structure sent via the request body payload
     * @param professorId the {@link UUID} referencing the professor teaching the course
     * @return a {@link ResponseEntity} presenting either the generated course response or a detailed failure trace
     */
    @PostMapping("/professor/{professorId}")
    @PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
    public ResponseEntity<?> createCourse(@RequestBody Course course, @PathVariable UUID professorId) {
        try {
            Course createdCourse = courseService.createCourse(course, professorId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the course.");
        }
    }
}