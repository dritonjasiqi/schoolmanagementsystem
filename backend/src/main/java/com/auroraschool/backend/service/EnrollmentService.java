package com.auroraschool.backend.service;

import com.auroraschool.backend.model.Enrollment;
import com.auroraschool.backend.model.EnrollmentStatus;

import java.util.List;
import java.util.UUID;

/**
 * Service layer interface defining core business logic contracts for managing {@link Enrollment} entities.
 * <p>
 * This contract establishes operational boundaries for registering students into educational modules,
 * auditing a single student's registration profile history, and executing state lifecycle updates
 * such as processing approvals, dropouts, or waiting lists.
 * </p>
 *
 * @author Driton Jasiqi
 * @see Enrollment
 * @see EnrollmentStatus
 * @see UUID
 */
public interface EnrollmentService {

    /**
     * Initializes a new academic registration process linking a designated student to a target course.
     * <p>
     * Implementation classes should validate that both target records exist, verify that the student
     * is not already actively registered for the exact same course, and instantiate a baseline
     * tracking record.
     * </p>
     *
     * @param studentId the unique {@link UUID} key tracking the student requesting entry
     * @param courseId  the unique {@link UUID} key tracking the course module being targeted
     * @return the newly instantiated, persisted, and managed {@link Enrollment} instance
     */
    Enrollment enrollStudentInCourse(UUID studentId, UUID courseId);

    /**
     * Aggregates a history index of every registration transaction associated with a single student profile.
     *
     * @param studentId the unique {@link UUID} identity tracking the student whose records are being audited
     * @return a {@link List} of matching {@link Enrollment} entities, which may be empty if the student
     * has not initiated any registration workflows
     */
    List<Enrollment> getEnrollmentsByStudent(UUID studentId);

    /**
     * Modifies the operational state phase of an existing student registration context.
     * <p>
     * Used primarily by administrative or automated auditing processes to transition application parameters
     * between processing phases (e.g., from pending to approved or rejected).
     * </p>
     *
     * @param enrollmentId the unique {@link UUID} key tracking the specific registration record to mutate
     * @param status       the targeted destination {@link EnrollmentStatus} step to apply
     * @return the updated and synchronized {@link Enrollment} entity instance returned by the persistence provider
     */
    Enrollment updateEnrollmentStatus(UUID enrollmentId, EnrollmentStatus status);
}