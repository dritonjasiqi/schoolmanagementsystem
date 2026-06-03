package com.auroraschool.backend.model;

/*
 * Package provides the necessary annotations used to define the relational
 * mapping between this Java Class and underlying ProgressSql Database via
 * the Persistence Provider
 */
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Entity representing a Course in the Aurora School system. This class extends the abstract User class.
 * It includes specific attributes relevant to course, such as name and price.
 * It includes specific relationship to the professor that manage the course and the students enrolled in it.
 */
@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {

    /**
     * Unique identifier for the course, generated automatically as a UUID.
     * This serves as the primary key for the course entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The name of the course, which is a required field and cannot be null in the database.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The price of the course, which is a required field and cannot be null in the database. It uses BigDecimal to ensure precision for monetary values.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * The professor who manages this course.
     * This field represents a many-to-one relationship where one professor can teach many courses.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "professor_id", nullable = false)
    private Professor professor;

    /**
     * A list of enrollments for this course.
     * This field represents a one-to-many relationship where one course can have many student enrollments.
     * Changes to enrollments are automatically cascaded and orphaned enrollments are removed.
     */
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();
}
