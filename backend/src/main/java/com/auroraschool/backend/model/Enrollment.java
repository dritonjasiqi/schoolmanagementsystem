package com.auroraschool.backend.model;

/*
 * Package provides the necessary annotations used to define the relational
 * mapping between this Java Class and underlying ProgressSql Database via
 * the Persistence Provider
 */
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
// Hibernate-specific auditing annotation to handle entity lifecycle timestamps
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing an Enrollment in the Aurora School system. This class models the relationship between a Student and a Course.
 * Contains attributes such as id, student, course enrollment status and timestamp of when the enrollment was created.
 */
@Entity
@Table(name = "enrollments")
@Getter
@Setter
public class Enrollment {
    /**
     * Primary key for this Enrollment. Generated as a UUID by the persistence provider.
     * Mapped to the primary key column of the enrollments table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The student who is enrolled in the course.
     * Many-to-one relationship (fetch = LAZY). Stored in column student_id and is required (non-null).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    /**
     * The course in which the student is enrolled.
     * Many-to-one relationship (fetch = LAZY). Stored in column course_id and is required (non-null).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    /**
     * The current status of the enrollment.
     * Persisted as the enum's String name. Column is non-nullable.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus enrollmentStatus;

    /**
     * The timestamp when this enrollment was created. Automatically populated by Hibernate
     * when the entity is persisted. Value is not updatable.
     */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime timestamp;

    /**
     * Default constructor required by JPA.
     */
    public Enrollment() {}

    public Enrollment(Student student, Course course, EnrollmentStatus enrollmentStatus) {
        this.student = student;
        this.course = course;
        this.enrollmentStatus = enrollmentStatus;
    }
}
