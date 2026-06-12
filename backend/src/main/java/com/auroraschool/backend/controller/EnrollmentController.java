package com.auroraschool.backend.controller;

import com.auroraschool.backend.model.Enrollment;
import com.auroraschool.backend.model.EnrollmentStatus;
import com.auroraschool.backend.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller layer component delivering REST endpoints for managing {@link Enrollment} life cycles.
 * <p>
 * This class exposes endpoints to register students into courses, view personal registration indices,
 * and modify transaction status phases. Security access boundaries are explicitly enforced on each endpoint
 * using Spring Security method-level expression validations.
 * </p>
 *
 * @author Driton Jasiqi
 * @see RestController
 * @see RequestMapping
 * @see EnrollmentService
 * @see PreAuthorize
 */
@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    /**
     * Core operational service managing relational registration business logic.
     */
    private final EnrollmentService enrollmentService;

    /**
     * Registers a targeted student into an explicit course context.
     * <p>
     * <b>Access Control:</b> Restricted strictly to authenticated profiles holding
     * the role of either {@code STUDENT} or {@code ADMIN}.
     * </p>
     *
     * @param courseId  the unique {@link UUID} tracking the desired academic course
     * @param studentId the unique {@link UUID} tracking the student profile requesting registration
     * @return a {@link ResponseEntity} containing the newly created {@link Enrollment} instance wrapped in an
     * HTTP 201 Created status block, an HTTP 400 Bad Request message on domain errors, or an
     * HTTP 500 Internal Server Error string on pipeline failures
     */
    @PostMapping("/{courseId}/student/{studentId}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public ResponseEntity<?> enrollStudent(@PathVariable UUID courseId, @PathVariable UUID studentId) {
        try {
            Enrollment enrollment = enrollmentService.enrollStudentInCourse(studentId, courseId);
            return ResponseEntity.status(HttpStatus.CREATED).body(enrollment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An Error occurred during Enrollment.");
        }
    }

    /**
     * Aggregates a compilation list of every enrollment record linked to a targeted student.
     * <p>
     * <b>Access Control:</b> Universally available to authenticated accounts holding
     * the role of {@code STUDENT}, {@code PROFESSOR}, or {@code ADMIN}.
     * </p>
     *
     * @param studentId the unique {@link UUID} tracking the student whose transcripts are being audited
     * @return a {@link ResponseEntity} holding a {@link List} of matching {@link Enrollment} entities
     * wrapped in a standard HTTP 200 OK block
     */
    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('STUDENT','PROFESSOR','ADMIN')")
    public ResponseEntity<List<Enrollment>> getStudentEnrollments(@PathVariable UUID studentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudent(studentId));
    }

    /**
     * Modifies the current verification stage or status flag of a designated enrollment transaction.
     * <p>
     * <b>Access Control:</b> Privileged endpoint restricted strictly to administrative supervisors
     * and faculty instructors holding the role of {@code ADMIN} or {@code PROFESSOR}.
     * </p>
     *
     * @param enrollmentId the unique {@link UUID} key tracking the registration row to mutate
     * @param status       the destination query parameter {@link EnrollmentStatus} to apply
     * @return a {@link ResponseEntity} containing the modified {@link Enrollment} object wrapped in an
     * HTTP 200 OK block, or an HTTP 404 Not Found error body if the target tracker record does not exist
     */
    @PutMapping("/{enrollmentId}/status")
    @PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
    public ResponseEntity<?> updateEnrollmentStatus(@PathVariable UUID enrollmentId, @RequestParam EnrollmentStatus status) {
        try {
            Enrollment updatedEnrollment = enrollmentService.updateEnrollmentStatus(enrollmentId, status);
            return ResponseEntity.ok(updatedEnrollment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}