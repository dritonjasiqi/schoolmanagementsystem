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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

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
     * <li>Enables CORS with a custom configuration source to allow cross-origin requests from trusted origins.</li>
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
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
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

    /**
     * Configures Cross-Origin Resource Sharing (CORS) for the application.
     * <p>
     * This bean defines a {@link CorsConfigurationSource} that permits a specific origin
     * (e.g., a local frontend development server running on port 3000) to safely interact
     * with the backend APIs. It specifies the allowed HTTP methods, headers, and enables
     * the transmission of authentication credentials (cookies, authorization headers, etc.).
     * </p>
     *
     * @return a configured {@link UrlBasedCorsConfigurationSource} applying these CORS
     * rules across all application paths ({@code "/**"})
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}