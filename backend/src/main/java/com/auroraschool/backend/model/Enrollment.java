package com.auroraschool.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Database entity representing a Student's registration context within a unique Course.
 * <p>
 * This entity acts as an explicit, join-table model establishing a many-to-one relationship
 * between a {@link Student} and a {@link Course}. It records supporting operational metadata,
 * including lifecycle status flags and auditable system creation timestamps.
 * </p>
 *
 * @author Driton Jasiqi
 * @see Entity
 * @see Table
 * @see Student
 * @see Course
 * @see EnrollmentStatus
 */
@Entity
@Table(name = "enrollments")
@Getter
@Setter
public class Enrollment {

    /**
     * Unique operational token representing the enrollment instance, automatically generated as a {@link UUID}.
     * This field serves as the primary key within the underlying relational table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The student seeking registration in the target course.
     * <p>
     * Maps a many-to-one relationship using the {@code student_id} foreign key database column.
     * Enforces a non-nullable constraint at the schema layer and leverages {@link FetchType#LAZY}
     * to prevent eager database loading overhead.
     * </p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    /**
     * The educational course target requested by the student.
     * <p>
     * Maps a many-to-one relationship using the {@code course_id} foreign key database column.
     * Enforces a non-nullable constraint at the schema layer and leverages {@link FetchType#LAZY}
     * to defer query fetching until explicitly invoked in code.
     * </p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    /**
     * The structural execution state processing phase tracking this application.
     * <p>
     * Evaluates and stores the contextual type using {@link EnumType#STRING} values inside the
     * database table to optimize clarity and prevent mapping bugs if the underlying enum
     * indexes shift. This field is required and cannot be null.
     * </p>
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus enrollmentStatus;

    /**
     * The precise date and system execution runtime clock point capturing when this registration was committed.
     * <p>
     * Utilizes Hibernate's custom {@link CreationTimestamp} core extension to automatically assign system
     * clock offsets when generating the baseline SQL transaction statement. This column is explicit and
     * locked against future database update mutations via {@code updatable = false}.
     * </p>
     */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime timestamp;

    /**
     * Default no-argument constructor required by the JPA Specification to facilitate runtime reflection
     * and dynamic initialization routines.
     */
    public Enrollment() {}

    /**
     * Overloaded constructor parameterized to quickly instantiate an active registration state mapping
     * tracking a specific student context inside a designated school course module.
     *
     * @param student          the concrete {@link Student} seeking entry
     * @param course           the concrete {@link Course} module being targeted
     * @param enrollmentStatus the baseline processing {@link EnrollmentStatus} profile to attach
     */
    public Enrollment(Student student, Course course, EnrollmentStatus enrollmentStatus) {
        this.student = student;
        this.course = course;
        this.enrollmentStatus = enrollmentStatus;
    }
}