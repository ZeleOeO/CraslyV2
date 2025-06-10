package com.zele.crasly_v2.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            handleError(response, HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    private void handleError(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");

        String body = String.format("""
                    {
                      "status": %d,
                      "error": "%s",
                      "message": "%s",
                      "timestamp": %d
                    }
                """, status.value(), status.getReasonPhrase(), message, System.currentTimeMillis());

        response.getWriter().write(body);

    }
}
