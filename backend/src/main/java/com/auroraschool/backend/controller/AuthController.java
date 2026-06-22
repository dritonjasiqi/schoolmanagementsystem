package com.auroraschool.backend.controller;

import com.auroraschool.backend.exception.EmailExistException;
import com.auroraschool.backend.model.LoginRequest;
import com.auroraschool.backend.model.Professor;
import com.auroraschool.backend.model.Student;
import com.auroraschool.backend.security.JwtService;
import com.auroraschool.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import java.util.Map;
import java.util.HashMap;

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
     * Authenticates a user credential and issues a secure JWT cookie upon success.
     * <p>
     * This method processes the login request by validating the provided email and
     * password against the authentication manager. If authentication is successful,
     * it extracts the user details, generates a JSON Web Token (JWT), encapsulates
     * it within a secure, {@code HttpOnly} cookie, and returns the user's profile information.
     * </p>
     *
     * @param request the {@link LoginRequest} data transfer object containing the user's email and password.
     * @return a {@link ResponseEntity} containing:
     * <ul>
     * <li>An HTTP 200 OK status with the {@code Set-Cookie} header and a {@link Map}
     * body holding the user's {@code email} and stripped {@code role} (e.g., "STUDENT").</li>
     * <li>An HTTP 401 Unauthorized status with an error message string if authentication fails.</li>
     * </ul>
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            final String jwt = jwtService.generateToken(userDetails);

            ResponseCookie jwtCookie = ResponseCookie.from("jwt_token", jwt)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(15 * 60)
                    .sameSite("Lax")
                    .build();

            Map<String,String> responseBody = new HashMap<>();
            responseBody.put("email", userDetails.getUsername());
            String role = userDetails.getAuthorities().iterator().next().getAuthority();
            responseBody.put("role", role.replace("ROLE_", ""));

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .body(responseBody);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }
}