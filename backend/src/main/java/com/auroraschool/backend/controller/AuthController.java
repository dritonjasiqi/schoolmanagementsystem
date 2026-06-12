package com.auroraschool.backend.controller;

import com.auroraschool.backend.exception.EmailExistException;
import com.auroraschool.backend.model.LoginRequest;
import com.auroraschool.backend.model.Professor;
import com.auroraschool.backend.model.Student;
import com.auroraschool.backend.security.JwtService;
import com.auroraschool.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * Authenticates incoming credentials and returns a stateless JSON Web Token (JWT) on success.
     * <p>
     * <b>Operational Routine:</b>
     * <ol>
     * <li>Wraps the user's plain credentials into an unauthenticated {@link UsernamePasswordAuthenticationToken}.</li>
     * <li>Hands execution to the {@link AuthenticationManager}. If credentials match the hashed
     * database record, processing completes cleanly; otherwise, an internal exception drops into the catch block.</li>
     * <li>Upon successful validation, loads the corresponding user entity details out of persistent storage.</li>
     * <li>Seals the profile data into a compacted JWT bearer token and flushes it back to the client.</li>
     * </ol>
     * </p>
     *
     * @param request a {@link LoginRequest} data transfer record holding the raw email and password strings
     * @return a {@link ResponseEntity} containing the signed JWT string wrapped in a standard HTTP 200 OK block,
     * or an HTTP 401 Unauthorized status message if verification checks fail
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
        final String jwt = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }
}