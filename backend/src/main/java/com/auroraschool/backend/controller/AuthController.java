package com.auroraschool.backend.controller;

import com.auroraschool.backend.exception.EmailExistException;
import com.auroraschool.backend.model.Professor;
import com.auroraschool.backend.model.Student;
import com.auroraschool.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposing Authentication-related endpoints under {@code /api/auth}.
 * <p>
 * This class handles incoming HTTP requests related to user registration and onboarding.
 * It leverages constructor-based dependency injection via Lombok's {@link RequiredArgsConstructor}
 * to interact with the underlying {@link UserService}.
 * </p>
 *
 * @author Driton Jasiqi
 * @see RestController
 * @see RequestMapping
 * @see UserService
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    /**
     * Service layer dependency handling business logic for user management.
     */
    private final UserService userService;

    /**
     * Registers a new student in the system.
     * <p>
     * Expected HTTP status outcomes:
     * <ul>
     * <li><b>201 Created:</b> Registration successful; returns the persisted student record.</li>
     * <li><b>409 Conflict:</b> The provided email address is already registered in the system.</li>
     * <li><b>500 Internal Server Error:</b> An unexpected exception occurred during processing.</li>
     * </ul>
     * </p>
     *
     * @param student the {@link Student} object deserialized from the HTTP request body containing registration details
     * @return a {@link ResponseEntity} containing either the created student payload or a descriptive error message
     */
    @PostMapping("/register/Student")
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {
        try {
            Student createdStudent = userService.addStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
        } catch (EmailExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while registering the student.");
        }
    }

    /**
     * Registers a new professor in the system.
     * <p>
     * Expected HTTP status outcomes:
     * <ul>
     * <li><b>201 Created:</b> Registration successful; returns the persisted professor record.</li>
     * <li><b>409 Conflict:</b> The provided email address is already registered in the system.</li>
     * <li><b>500 Internal Server Error:</b> An unexpected exception occurred during processing.</li>
     * </ul>
     * </p>
     *
     * @param professor the {@link Professor} object deserialized from the HTTP request body containing registration details
     * @return a {@link ResponseEntity} containing either the created professor payload or a descriptive error message
     */
    @PostMapping("/register/Professor")
    public ResponseEntity<?> registerProfessor(@RequestBody Professor professor) {
        try {
            Professor createdProfessor = userService.addProfessor(professor);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProfessor);
        } catch (EmailExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while registering the professor.");
        }
    }
}