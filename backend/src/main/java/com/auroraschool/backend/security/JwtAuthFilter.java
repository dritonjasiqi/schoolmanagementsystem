package com.auroraschool.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Custom JWT Authentication Filter that intercepts incoming HTTP requests to validate
 * JSON Web Tokens (JWT) provided in the {@code Authorization} header.
 * <p>
 * This filter extends {@link OncePerRequestFilter} to guarantee execution exactly once per
 * request dispatch. If a valid JWT security token is located, the filter extracts the user identity,
 * resolves the user permissions profile, and configures Spring Security's central
 * {@link SecurityContextHolder} to authenticate the current execution thread context.
 * </p>
 *
 * @author Driton Jasiqi
 * @see OncePerRequestFilter
 * @see JwtService
 * @see UserDetailsService
 */
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    /**
     * Service layer dependency handling operations for token parsing, extraction, and validation.
     */
    private final JwtService jwtService;

    /**
     * Core contract dependency used to load security details based on unique account usernames.
     */
    private final UserDetailsService userDetailsService;

    /**
     * Intercepts the HTTP request pipeline to verify incoming JWT bearer strings and set the
     * authentication context.
     * <p>
     * <b>Operational Workflow:</b>
     * <ol>
     * <li>Inspects the incoming {@code Authorization} request header for a valid "Bearer " token string template.</li>
     * <li>If absent, hands processing immediately over to the subsequent filter down the chain.</li>
     * <li>Extracts the raw token content and evaluates the associated username signature.</li>
     * <li>If a valid identity is resolved and the application context lacks active authentication details,
     * it invokes the {@link UserDetailsService}.</li>
     * <li>Validates whether the token remains structurally sound and matches the registered {@link UserDetails}.</li>
     * <li>If valid, builds a new {@link UsernamePasswordAuthenticationToken}, attaches request metadata,
     * and binds it directly inside the active {@link SecurityContextHolder}.</li>
     * <li>Hands execution forward down the standard {@link FilterChain}.</li>
     * </ol>
     * </p>
     *
     * @param request     the incoming {@link HttpServletRequest} profile containing header arrays
     * @param response    the outbound {@link HttpServletResponse} execution pathway
     * @param filterChain the foundational {@link FilterChain} driving the web container interception security rules
     * @throws ServletException if a container-specific servlet processing exception occurs
     * @throws IOException      if an input/output network error drops processing streams
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}