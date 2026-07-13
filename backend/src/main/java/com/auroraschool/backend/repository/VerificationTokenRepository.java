package com.auroraschool.backend.repository;

import com.auroraschool.backend.model.VerificationToken;
import com.auroraschool.backend.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;

/**
 * Data access repository for managing {@link VerificationToken} entities.
 *
 * <p>Provides standard CRUD operations inherited from {@link JpaRepository}
 * along with custom query methods for retrieving tokens by their unique
 * string values or associated user records.</p>
 *
 * @author Driton Jasiqi
 * @see VerificationToken
 * @see JpaRepository
 */
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, UUID> {

    /**
     * Retrieves a verification token by its unique string value.
     *
     * @param token the unique token string to search for
     * @return an {@link Optional} containing the found {@link VerificationToken},
     *         or an empty {@link Optional} if no match is found
     */
    Optional<VerificationToken> findByToken(String token);

    /**
     * Retrieves the verification token associated with a specific user.
     *
     * @param user the {@link User} whose token is being queried
     * @return an {@link Optional} containing the associated {@link VerificationToken},
     *         or an empty {@link Optional} if none exists for the given user
     */
    Optional<VerificationToken> findByUser(User user);
}