package com.auroraschool.backend.controller;

import com.auroraschool.backend.exception.EmailExistException;
import com.auroraschool.backend.model.Professor;
import com.auroraschool.backend.model.Student;
import com.auroraschool.backend.service.UserService;

// Generates a constructor for all final fields, enforcing clean constructor injection.
import lombok.RequiredArgsConstructor;
//Enum providing standard Http codes
import org.springframework.http.HttpStatus;
//Wrapper for Http Response, allowing to set status , body and header
import org.springframework.http.ResponseEntity;
//annotations used to map HTTP requests to your Java methods
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposing Authentication-related endpoints under /api/auth.
 * Provides operations for user registration and authentication.
 */
@RestController // Marks the class as a web controller where every method returns an object  rather than a view
@RequestMapping("/api/auth") // Defines the base URL path for the controller.
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    /**
     * Registers a new student.
     * @param student Student object containing the registration details for a new student. The request body should include necessary fields such as name, email, enrollment number, etc.
     * @return ResponseEntity with the created student or an error message.
     */
    @PostMapping("/register/Student")
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {
        try {
            Student createdStudent = userService.addStudent(student);
            return  ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
        } catch (EmailExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while registering the student.");
        }
    }

    /**
     * Registers a new professor.
     * @param professor Professor object containing the registration details for a new professor. The request body should include necessary fields such as name, email, personal email, etc.
     * @return ResponseEntity with the created professor or an error message.
     */
    @PostMapping("/register/Professor")
    public ResponseEntity<?> registerProfessor(@RequestBody Professor professor){
        try {
            Professor createdProfessor = userService.addProfessor(professor);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProfessor);
        } catch (EmailExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while registering the professor.");
        }
    }
}
