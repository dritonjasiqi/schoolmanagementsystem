package com.auroraschool.backend.model;
/*
 * Package provides the necessary annotations used to define the relational
 * mapping between this Java Class and underlying ProgressSql Database via
 * the Persistence Provider
 */
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a Student in the Aurora School system. This class extends the abstract User class.
 * It includes specific attributes and relationships relevant to students, such as enrollment number and the courses they are enrolled in.
 */
@Entity
@Table(name = "students")
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User{
    /**
     * Unique enrollmentNumber for students that will get used to enroll Classes.
     */
    @Column(unique = true, nullable = false)
    private Long enrollmentNumber;

    /**
     * A List of Enrollment associated with this student
     * This field represents a one-to-many Relationship. One user can have many Enrollments
     * All lifecycle operations (persist, remove, refresh, merge)
     * applied to this user will automatically propagate to the associated {@code Enrollment} objects.
     * mappedByStudent indicates that student field in Enrollemnt table owns the Relationship
     * If an Enrollments is removed in this list it will get removen also in Database
     */
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student", orphanRemoval = true)
    private List<Enrollment> courses = new ArrayList<>();
}
