package com.auroraschool.backend.config;

import com.auroraschool.backend.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Main Security Configuration Class.
 * <p>
 * This class configures HTTP security rules, endpoint permissions, and session management
 * to establish a stateless, token-based security architecture for the application.
 * </p>
 *
 * @author Driton Jasiqi
 * @see Configuration
 * @see EnableWebSecurity
 * @see EnableMethodSecurity
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * Custom JWT authentication filter used to validate tokens on incoming requests.
     */
    private final JwtAuthFilter jwtAuthFilter;

    /**
     * Configures the main security filter chain for all incoming HTTP requests.
     * <p>
     * This method applies the following core security behaviors:
     * <ul>
     * <li>Disables CSRF (Cross-Site Request Forgery) protection, as the application uses stateless JWTs.</li>
     * <li>Defines access control rules, allowing public access to authentication endpoints while securing all other routes.</li>
     * <li>Enforces a stateless session management policy (no HTTP sessions are created or maintained).</li>
     * <li>Registers the custom {@link JwtAuthFilter} ahead of the standard {@link UsernamePasswordAuthenticationFilter}.</li>
     * </ul>
     * </p>
     *
     * @param http the {@link HttpSecurity} object used to build the security configuration pipeline
     * @return the fully configured {@link SecurityFilterChain}
     * @throws Exception if an error occurs while configuring or building the security chain
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/api/auth/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}