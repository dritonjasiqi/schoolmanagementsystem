package com.auroraschool.backend.service;

import java.util.UUID;

import com.auroraschool.backend.model.Admin;
import com.auroraschool.backend.model.Professor;
import com.auroraschool.backend.model.Student;
import com.auroraschool.backend.model.User;

/**
 * Service layer interface defining core business logic contracts for managing {@link User} profiles.
 * <p>
 * This contract establishes operational boundaries for the creation, retrieval, modification,
 * and deletion of user contexts across all sub-types including {@link Student}, {@link Professor},
 * and {@link Admin} entities within the system.
 * </p>
 *
 * @author Driton Jasiqi
 * @see User
 * @see Student
 * @see Professor
 * @see Admin
 * @see UUID
 */
public interface UserService {

    /**
     * Purges a user account completely from persistent storage based on its unique relational database token.
     *
     * @param id the unique {@link UUID} identity tracking the user account to delete
     */
    void removeUser(UUID id);

    /**
     * Locates a single user profile based on their primary system username signature.
     *
     * @param username the username string representing the user identity to query
     * @return the matching {@link User} entity context located by the system lookup routines
     */
    User getUserByUsername(String username);

    /**
     * Locates a single user profile based on their unique identity email address.
     *
     * @param email the unique email identity string tracking the target user account
     * @return the matching {@link User} entity context located by the system lookup routines
     */
    User getUserByEmail(String email);

    /**
     * Locates a single user profile based on its unique relational database token.
     *
     * @param id the unique {@link UUID} key tracking the desired user record
     * @return the matching {@link User} entity context located by the system lookup routines
     */
    User getUserById(UUID id);

    /**
     * Updates an existing user record with fresh profile state values.
     * <p>
     * <b>Prerequisite:</b> The incoming entity instance must contain a valid, non-null {@link UUID}
     * matching an active database entry to trigger a successful state mutation pipeline.
     * </p>
     *
     * @param user the {@link User} entity instance encapsulating the modified fields to synchronize
     * @return the fully updated and merged {@link User} instance returned by the persistence provider
     */
    User updateUser(User user);

    /**
     * Registers and persists a new student account inside the system database.
     *
     * @param student the unpersisted {@link Student} profile containing mandatory schema credentials
     * @return the fully persisted and managed {@link Student} instance including database identifiers
     */
    Student addStudent(Student student);

    /**
     * Registers and persists a new professor account inside the system database.
     *
     * @param professor the unpersisted {@link Professor} profile containing mandatory schema credentials
     * @return the fully persisted and managed {@link Professor} instance including database identifiers
     */
    Professor addProfessor(Professor professor);

    /**
     * Registers and persists a new administrator account inside the system database.
     *
     * @param admin the unpersisted {@link Admin} profile containing mandatory schema credentials
     * @return the fully persisted and managed {@link Admin} instance including database identifiers
     */
    Admin addAdmin(Admin admin);
}