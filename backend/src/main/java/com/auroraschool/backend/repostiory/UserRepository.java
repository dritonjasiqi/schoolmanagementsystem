package com.auroraschool.backend.repostiory;

import com.auroraschool.backend.model.User;
// Provides standard JPA CRUD operations and query method support.
import org.springframework.data.jpa.repository.JpaRepository;
// Marks this component for Spring's component scanning and
// enables exception translation
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for User Entity, extending JpaRepository for CRUD Operation and custom query methods.
 * It includes methods to check if a user exists by email.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * Give the User associated with this Email if he exists.
     * @param email Email of the user
     * @return User or Null
     */
    Optional<User> findByEmail(String email);

    /**
        * Stores Value of a User exists with this email
     */
    boolean existsByEmail(String email);
}
