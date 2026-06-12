package com.auroraschool.backend.service;

import com.auroraschool.backend.model.Course;
import com.auroraschool.backend.model.Enrollment;
import com.auroraschool.backend.model.EnrollmentStatus;
import com.auroraschool.backend.model.Student;
import com.auroraschool.backend.repository.CourseRepository;
import com.auroraschool.backend.repository.EnrollmentRepository;
import com.auroraschool.backend.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class EnrollmentServiceClass implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
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
    @Override
    public Enrollment enrollStudentInCourse(UUID studentId, UUID courseId) {
        if (enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new IllegalArgumentException("Student is already enrolled in this course.");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found."));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found."));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentStatus(EnrollmentStatus.ACTIVE);

        return enrollmentRepository.save(enrollment);
    }

    /**
     * Aggregates a history index of every registration transaction associated with a single student profile.
     *
     * @param studentId the unique {@link UUID} identity tracking the student whose records are being audited
     * @return a {@link List} of matching {@link Enrollment} entities, which may be empty if the student
     * has not initiated any registration workflows
     */
    @Override
    public List<Enrollment> getEnrollmentsByStudent(UUID studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

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
    @Override
    public Enrollment updateEnrollmentStatus(UUID enrollmentId, EnrollmentStatus status) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found."));

        enrollment.setEnrollmentStatus(status);
        return enrollmentRepository.save(enrollment);
    }
}
