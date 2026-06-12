package com.auroraschool.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service handling operations for JSON Web Tokens (JWT), including creation,
 * signature validation, and claims parsing for secure user authentication.
 * <p>
 * This bean encapsulates the application's cryptographic routines. It processes
 * standard claims such as token subjects and expiration offsets, mapping them
 * against Spring Security's structural {@link UserDetails} contract to protect
 * stateless endpoint transactions.
 * </p>
 *
 * @author Driton Jasiqi
 * @see Service
 * @see Jwts
 * @see Claims
 * @see UserDetails
 */
@Service
public class JwtService {

    /**
     * Secret cryptographic key used to sign and verify generated tokens.
     * <p>
     * Loaded as a Base64-encoded string parameter from the externalized
     * {@code application.properties} configuration layer. It falls back to a
     * hardcoded test string if no explicit environment variable is declared.
     * </p>
     */
    @Value("${application.security.jwt.secret-key:404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970}")
    private String secretKey;

    /**
     * Extracts the primary identification username (subject) encoded inside a given JWT.
     *
     * @param token the raw JWT token string to evaluate
     * @return the username subject embedded within the token payload
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Decodes the configured Base64 secret key string and wraps it into a proper cryptographic object.
     * <p>
     * The resulting {@link Key} is utilized by underlying algorithms to structurally verify
     * token authenticity and validate payload contents against tamper vectors.
     * </p>
     *
     * @return a concrete {@link Key} object optimized for HMAC-SHA signature verifications
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Compiles and decrypts a target token string to reveal its full inner claims payload.
     *
     * @param token the signed cryptographic JWT string to parse
     * @return the complete {@link Claims} descriptor dictionary extracted from the token body
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Extracts a specialized singular claim descriptor from a token using a functional resolver mapping.
     *
     * @param <T>            the generic type structure of the expected output target
     * @param token          the raw signed token string to parse
     * @param claimsResolver a functional {@link Function} interface mapping compiled claims onto type T
     * @return the calculated evaluation value of the targeted claim parameter
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claim = extractAllClaims(token);
        return claimsResolver.apply(claim);
    }

    /**
     * Generates a baseline security token for an authenticated user context profile.
     *
     * @param userDetails the core identity record representing the active user account
     * @return a signed, standard JWT authorization string
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Compiles a custom, signed JSON Web Token holding standard parameters alongside
     * structural custom properties.
     * <p>
     * Appends the user's username as the token subject, establishes generation and expiration
     * timestamps, and seals the final payload string utilizing the {@link SignatureAlgorithm#HS256}
     * algorithm specification.
     * </p>
     *
     * @param extraClaims  a {@link Map} containing key-value metadata parameters to embed inside the token body
     * @param userDetails the core identity context tracking the system entity
     * @return a fully compacted and cryptographically sealed token string
     */
    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extracts the specific system runtime timeout timestamp bound to a given token string.
     *
     * @param token the raw signed token string to parse
     * @return the precise {@link Date} tracking when this token loses system validity
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Determines whether a given token has surpassed its assigned session expiration timestamp.
     *
     * @param token the raw signed token string to check
     * @return {@code true} if the current system clock is past the token's expiration date;
     * {@code false} otherwise
     */
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Validates whether a token is structurally sound, unexpired, and explicitly bound to a
     * matching user context.
     *
     * @param token       the raw signed token string to evaluate
     * @param userDetails the core user record to test against the token's embedded data contents
     * @return {@code true} if the token subject matches the user credentials and the expiration window
     * remains open; {@code false} if any check fails
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
}