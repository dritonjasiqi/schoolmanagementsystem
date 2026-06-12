package com.auroraschool.backend.config;

import com.auroraschool.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Core application configuration class responsible for orchestrating Spring Security infrastructure beans.
 * <p>
 * This configuration assembles the foundational authentication pipeline by providing implementations for
 * identity resolution via the database layer, password encryption strategies, and structural authentication
 * management providers.
 * </p>
 *
 * @author Driton Jasiqi
 * @see Configuration
 * @see UserDetailsService
 * @see AuthenticationProvider
 * @see AuthenticationManager
 * @see PasswordEncoder
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    /**
     * Data access engine used within identity lookup streams to extract user data out of persistent storage.
     */
    private final UserRepository userRepository;

    /**
     * Establishes the standard bridge between your custom database user entities and Spring Security's internal ecosystem.
     * <p>
     * Overrides load operations to query accounts utilizing email addresses as the principal username.
     * If a record is found, it maps domain attributes onto Spring's core {@link User} details builder template.
     * </p>
     *
     * @return a functional lambda execution mapping standard lookups onto database queries
     * @throws UsernameNotFoundException if no user profile matches the requested identification string
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            com.auroraschool.backend.model.User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User with this email not found"));

            return User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();
        };
    }

    /**
     * Configures a standard data-access authentication manager bean.
     * <p>
     * Instantiates a {@link DaoAuthenticationProvider}, binding your customized identity verification rule sets
     * alongside your selected hashing engine to evaluate user credentials during login handshakes.
     * </p>
     *
     * @return a fully tailored authentication provider component
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Extracts and exposes Spring Security's native central dispatcher interface from the shared runtime context.
     *
     * @param config the global authentication management builder state
     * @return the central application {@link AuthenticationManager} engine
     * @throws Exception if processing configurations fall out of structural container boundaries
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Defines the encryption standard utilized across the system to protect raw password tokens.
     * <p>
     * Employs the industry-standard {@link BCryptPasswordEncoder} one-way adaptive hashing algorithm
     * to safely secure credentials before comparing or storing them in the relational database.
     * </p>
     *
     * @return a secure Bcrypt password encoding instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}