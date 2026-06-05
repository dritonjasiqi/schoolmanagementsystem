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



public class JwtService {
}
