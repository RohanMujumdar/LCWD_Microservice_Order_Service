package com.example.orderService_microservice.orderService.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class StaticTokenAuthFilter extends OncePerRequestFilter {

    private static final String AUTH_TOKEN = "Bearer xyz123";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");


        if (request.getRequestURI().startsWith("/micro/api/order/")) {
            if (authHeader == null || !authHeader.equals(AUTH_TOKEN)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing Authorization header");
                return;
            }

            // ✅ Set dummy authenticated user
            // We have to set our users in the security context first only then APIs are allowed to access.
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken("gateway-user", null, Collections.emptyList())
            );
        }

        filterChain.doFilter(request, response);
    }
}
