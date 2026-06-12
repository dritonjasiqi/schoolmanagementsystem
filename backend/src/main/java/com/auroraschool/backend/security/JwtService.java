package com.auroraschool.backend.security;

//Represents the "Payload" of the token. Contains the actual Data
import io.jsonwebtoken.Claims;
// Main Jwt Class. Used to create or parse
import io.jsonwebtoken.Jwts;
//Cryptographic algorithm for token security
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
//Takes Bytes from Decoder and create a secure Key Object, JJWT uses it for signing the token
import io.jsonwebtoken.security.Keys;

//Allows you to inject properties directly from your application.properties
import org.springframework.beans.factory.annotation.Value;
// Spring Security's core user interface.
// When a professor, student, or admin logs in, Spring represents them as a UserDetails object.
import org.springframework.security.core.userdetails.UserDetails;
//Register class as Bean
import org.springframework.stereotype.Service;

//cryptographic key for signing the token, stored as a Base64-encoded string in application.properties
import java.security.Key;
import java.util.Date;
// Data Structure with keys and values
import java.util.HashMap;
import java.util.Map;
// Functional Interface
import java.util.function.Function;

/**
 * Service for handling JSON Web Token (JWT) operations, including
 * parsing, generation, and validation for secure user authentication.
 */
@Service // Marks class
public class JwtService {

    /**
     * Secret Key. Only for Testing. Real World Application should be handled with an environment variable.
     */
    @Value("${application.security.jwt.secret-key:404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970}")
    private String secretKey;

    /**
     * Extract the username from the provided JWT
     * @param token JWT Token
     * @return username contained in Token
     */
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Decodes the secret Key and generates a signing key for Verification of the token
     * @return Cryptographic Key Object
     */
    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Parses the token to retrieve all claims stored within.
     * @param token JWT Token
     * @return Claims set from the token
     */
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    /**
     * Extracts a specific claim from the token with help of a functional Interface
     * @param token JWT token
     * @param claimsResolver Function to extract the specific claim
     * @return The requested claim
     */
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claim = extractAllClaims(token);
        return claimsResolver.apply(claim);
    }

    /**
     * Generates a new Token for the specified user details
     * @param userDetails The user to generate the Token for
     * @return A signed JWT string
     */
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Internal method to generate a token with optional extra claims.
     * @param extraClaims A map of additional claims to include in the token.
     * @param userDetails The user details.
     * @return A signed JWT string.
    */
    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1000 *60 *24)).signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    /**
     * Extract the expiration Date from the Token
     * @param token JWT Token
     * @return Expiration Date
     */
    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Checks if the token has expired
     * @param token JWT Token
     * @return True if token is expired, orElse False
     */
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    /**
     * Checks if the token belongs to the User and hasnt expired
     * @param token JWT Token
     * @param userDetails The user in which will be applied the Validation
     * @return True if valid, orElse False
     */
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()))  && !isTokenExpired(token);
    }
}
