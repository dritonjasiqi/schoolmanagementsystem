package com.auroraschool.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
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
import java.util.Arrays;

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
     * Intercepts incoming HTTP requests to validate and process JSON Web Tokens (JWT)
     * for user authentication.
     * <p>
     * This method executes once per request. It attempts to extract a JWT from either:
     * <ol>
     * <li>A cookie named {@code "jwt_token"} (primary source for web clients).</li>
     * <li>The {@code Authorization} bearer header (fallback for APIs, Postman, or mobile clients).</li>
     * </ol>
     * If a valid token is found and the user is not already authenticated within the
     * current {@link SecurityContextHolder}, the user's details are loaded, verified,
     * and a new {@link UsernamePasswordAuthenticationToken} is established in the security context.
     * </p>
     *
     * @param request     the incoming {@link HttpServletRequest} object containing client request details.
     * @param response    the {@link HttpServletResponse} object used to pass responses back to the client.
     * @param filterChain the {@link FilterChain} used to invoke the next filter in the security architecture.
     * @throws ServletException if a servlet-specific error occurs during processing.
     * @throws IOException      if an I/O exception occurs during the execution of the filter chain.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = null;
        String userEmail = null;
        if (request.getCookies() != null) {
            jwt = Arrays.stream(request.getCookies())
                    .filter(cookie -> "jwt_token".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }
        //POSTMAN
        if (jwt == null) {
            final String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                jwt = authHeader.substring(7);
            }
        }


        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }
        userEmail = jwtService.extractUsername(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

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