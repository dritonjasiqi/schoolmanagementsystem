package com.auroraschool.backend.controller;

import com.auroraschool.backend.exception.EmailExistException;
import com.auroraschool.backend.model.Professor;
import com.auroraschool.backend.model.Student;
import com.auroraschool.backend.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

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
