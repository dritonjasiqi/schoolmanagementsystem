package com.auroraschool.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Database entity representing a Course within the Aurora School system.
 * <p>
 * This class tracks core educational offerings, managing foundational attributes such as
 * pricing structures and titles. It serves as a central relational hub linking the managing
 * {@link Professor} to the collection of active student {@link Enrollment} records.
 * </p>
 *
 * @author Driton Jasiqi
 * @see Entity
 * @see Table
 * @see Professor
 * @see Enrollment
 */
@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {

    /**
     * Unique identifier for the course, automatically generated as a {@link UUID}.
     * This field serves as the primary key within the underlying relational table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The title or designation of the course. This field is required and cannot be null.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The financial cost of the course.
     * <p>
     * Utilizes {@link BigDecimal} to guarantee absolute fixed-point precision for monetary math.
     * It is mapped with a precision of 10 digits and a scale of 2 decimal places (e.g., 99999999.99),
     * and cannot be null.
     * </p>
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * The faculty member responsible for organizing and instructing this course.
     * <p>
     * This establishes a many-to-one mapping back to the {@link Professor} entity via the
     * {@code professor_id} foreign key column. It uses {@link FetchType#LAZY} loading to optimize
     * database query overhead, fetching relation data only when explicitly accessed.
     * </p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    /**
     * The collection of student registrations linked to this course.
     * <p>
     * Implements a bidirectional one-to-many relationship mapped by the {@code course} field
     * within the child {@link Enrollment} entity. State modifications cascade entirely down to
     * child rows ({@link CascadeType#ALL}), and orphaned database entries are automatically purged
     * from persistent storage if removed from this collection.
     * </p>
     */
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();
}