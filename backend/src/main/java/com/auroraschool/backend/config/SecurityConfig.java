package com.auroraschool.backend.config;

// Allows the definition of Beans inside configuration classes.
import org.springframework.context.annotation.Bean;
// Marks this class as a source of bean definitions to be processed by the Spring container during startup.
import org.springframework.context.annotation.Configuration;
// Enables method-level security, allowing you to use annotations like @PreAuthorize("hasRole('ADMIN')") directly on your methods.
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// The core configuration object used to build specific HTTP security rules, such as route protection and filters.
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// Activates Spring Security's web security support and integrates it with Spring MVC.
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// A base class providing a flexible way to disable or customize default Spring Security configurations (like CSRF) using lambdas.
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// An enum that defines the session management strategies (e.g., whether to use cookies/sessions or go stateless).
import org.springframework.security.config.http.SessionCreationPolicy;
// Represents the chain of security filters that every incoming HTTP request must pass through.
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
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
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //TODO JWT-Filter
        return http.build();
    }
}
