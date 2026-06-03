package com.auroraschool.backend.model;

/*
 * Package provides the necessary annotations used to define the relational
 * mapping between this Java Class and underlying ProgressSql Database via
 * the Persistence Provider
 */
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing an Admin in the Aurora School system. This class extends the abstract User class.
 * It includes specific attributes relevant to admin, such as clearanceLevel.
 */
@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
public class Admin extends User{
    /**
     * Clearance level of the admin, used to determine the level of access and permissions within the system.
     */
    @Column(nullable = false)
    private Integer clearanceLevel = 1;
}
