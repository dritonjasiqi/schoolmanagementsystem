package com.auroraschool.backend.controller;

import com.auroraschool.backend.model.Course;
import com.auroraschool.backend.service.CourseService;

// Generates a constructor for all final fields, enforcing clean constructor injection.
import lombok.RequiredArgsConstructor;
//Enum providing standard Http codes
import org.springframework.http.HttpStatus;
//Wrapper for Http Response, allowing to set status , body and header
import org.springframework.http.ResponseEntity;
// Package that allows to restrict access to a specific method based on a condition
import org.springframework.security.access.prepost.PreAuthorize;
//annotations used to map HTTP requests to your Java methods
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller exposing Course-related endpoints under /api/courses.
 * Provides operations to list courses, retrieve a single course by id, and
 * create a new course associated with a professor. Methods return standard HTTP
 * status codes: 200 for success, 201 for created, 404 when resources are not
 * found, and 500 for unexpected server errors.
 */
@RestController // Marks the class as a web controller where every method returns an object  rather than a view
@RequestMapping("/api/courses") // Defines the base URL path for the controller.
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    /**
     * Retrieve all courses.
     * @return ResponseEntity containing 200 OK and a list of courses (may be empty)
     */
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    /**
     * Retrieve a course by its id.
     * @param id UUID of the course to retrieve
     * @return 200 OK with the Course when found; 404 NOT FOUND when no course exists with the given id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable UUID id){
        try{
            return ResponseEntity.ok(courseService.getCourse(id));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Create a new course and associate it with a professor.
     * Requires the caller to have role  ADMIN or  PROFESSOR.
     * @param course the Course payload to create
     * @param professorId UUID of the professor to be associated with the course
     * @return 201 CREATED with the created Course on success; 404 NOT FOUND if the professor does not exist; 500 on unexpected errors
     */
    @PostMapping("/professor/{professorId}")
    @PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
    public ResponseEntity<?> createCourse(@RequestBody Course course, @PathVariable UUID professorId){
        try {
            Course createdCourse = courseService.createCourse(course, professorId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the course.");
        }
    }
}
