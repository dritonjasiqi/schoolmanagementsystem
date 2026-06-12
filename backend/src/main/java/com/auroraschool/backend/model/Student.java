package com.auroraschool.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Database entity representing a Student within the Aurora School system.
 * <p>
 * This class extends the abstract {@link User} base class using a joined inheritance strategy,
 * as indicated by the {@link PrimaryKeyJoinColumn} annotation linking it back to the core user table.
 * It tracks student-specific registration identities and maintains a collection of active
 * course enrollments.
 * </p>
 *
 * @author Driton Jasiqi
 * @see User
 * @see Entity
 * @see Table
 * @see Enrollment
 */
@Entity
@Table(name = "students")
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {

    /**
     * Unique academic identification number assigned to the student.
     * <p>
     * This field is used as a primary administrative key for processing course registrations
     * and academic lookups. It enforces both {@code unique} and {@code nullable = false}
     * constraints at the database schema layer.
     * </p>
     */
    @Column(unique = true, nullable = false)
    private Long enrollmentNumber;

    /**
     * The collection of registration records associated with this student.
     * <p>
     * Implements a bidirectional one-to-many relationship mapped by the {@code student} field
     * within the child {@link Enrollment} entity. State modifications cascade entirely down to
     * child rows ({@link CascadeType#ALL}), and orphaned database entries are automatically purged
     * from persistent storage if removed from this collection ({@code orphanRemoval = true}).
     * </p>
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student", orphanRemoval = true)
    private List<Enrollment> courses = new ArrayList<>();
}