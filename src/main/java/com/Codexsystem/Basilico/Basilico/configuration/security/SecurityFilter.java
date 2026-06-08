package com.Codexsystem.Basilico.Basilico.configuration.security;

import jakarta.persistence.Access;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenConfig tokenConfig;
/*
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            String authToken = token.substring("Bearer ".length());
            Optional<JwtDate> jwtDate = tokenConfig.validateToken(authToken);
            if (jwtDate.isPresent()) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        jwtDate.get().username(),
                        null,
                        null);

                filterChain.doFilter(request, response);
            }else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }*/

    //metodo acima estava barrando as requisições, criei esse para testar
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            String authToken = token.substring("Bearer ".length());
            Optional<JwtDate> jwtDate = tokenConfig.validateToken(authToken);
            if (jwtDate.isPresent()) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        jwtDate.get().username(),
                        null,
                        null);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
