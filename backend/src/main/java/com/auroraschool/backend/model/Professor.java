package com.auroraschool.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Database entity representing a Professor within the Aurora School system.
 * <p>
 * This class extends the abstract {@link User} base class using a joined inheritance strategy,
 * tracking faculty-specific attributes such as external contact emails and academic curricula vitae.
 * It serves as an active relationship root for associated registration lifecycle configurations.
 * </p>
 *
 * @author Driton Jasiqi
 * @see User
 * @see Entity
 * @see Table
 */
@Entity
@Table(name = "professors")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
public class Professor extends User {

    /**
     * The unique personal email address belonging to the professor.
     * <p>
     * Used primarily for external communications, multi-factor backup parameters, or
     * cross-system verification. This column enforces a unique constraint at the database layer.
     * </p>
     */
    @Column(unique = true)
    private String personalEmail;

    /**
     * An optional cloud storage URL pointing to the professor's academic Curriculum Vitae (CV).
     * <p>
     * This field is public-facing and provides students with visibility into the instructor's
     * professional and research background prior to or during course instruction.
     * </p>
     */
    private String cvUrl;

    /**
     * The collection of course context entries associated with this professor.
     * <p>
     * Establishes a bidirectional one-to-many relationship mapped by the {@code professor} field
     * within the child entity. State mutations, including persistence and deletion routines,
     * cascade entirely down to the related rows via {@link CascadeType#ALL}.
     * </p>
     */
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Course> courses;
}