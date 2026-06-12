package com.auroraschool.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Database entity representing an Administrator within the Aurora School system.
 * <p>
 * This class extends the abstract {@link User} base class using a joined inheritance strategy,
 * as indicated by the {@link PrimaryKeyJoinColumn} annotation linking it back to the core user table.
 * It holds specific operational attributes unique to system administrators, such as structural permission tiers.
 * </p>
 *
 * @author Driton Jasiqi
 * @see User
 * @see Entity
 * @see Table
 */
@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
public class Admin extends User {

    /**
     * The security clearance level assigned to this administrator.
     * <p>
     * This numerical tier is evaluated across the application to determine granular data access rights
     * and administrative execution privileges. It defaults to a baseline level of {@code 1} and cannot be null.
     * </p>
     */
    @Column(nullable = false)
    private Integer clearanceLevel = 1;
}