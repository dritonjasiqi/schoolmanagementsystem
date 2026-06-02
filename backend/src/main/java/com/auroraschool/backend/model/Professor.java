package com.auroraschool.backend.model;

/*
 * Package provides the necessary annotations used to define the relational
 * mapping between this Java Class and underlying ProgressSql Database via
 * the Persistence Provider
 */
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Entity representing a Professor in the Aurora School system. This class extends the abstract User class.
 * It includes specific attributes and relationships relevant to professor, such as personalEmail, cvUrl and the courses they manage.
 */
@Entity
@Table(name = "professors")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
public class  Professor extends User{
    /**
     * unique , personalEmail of the Professor , used for authentification
     */
    @Column(unique = true)
    private String personalEmail;

    /**
     * Optional,  Url of the professor CV in Cloud. Used for Student doubts about the course.
     */
    private String cvUrl;

    /**
     * A List of Enrollment managed by this Professor.
     * This field represents a one-to-many Relationship. One Professor can manage many Enrollments
     * All lifecycle operations (persist, remove, refresh, merge)
     * applied to this user will automatically propagate to the associated {@code Enrollment} objects.
     * mappedByProfessor indicates that professor field in Enrollemnt table owns the Relationship
     * If an Enrollments is removed in this list it will get removen also in Database
     */
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Enrollment> courses;
}
