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
 * Service for JWT.
 */
@Service // Marks class
public class JwtService {
    @Value("${application.security.jwt.secret-key:404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970}")
    private String secretKey;

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claim = extractAllClaims(token);
        return claimsResolver.apply(claim);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1000 *60 *24)).signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()))  && !isTokenExpired(token);
    }
}
