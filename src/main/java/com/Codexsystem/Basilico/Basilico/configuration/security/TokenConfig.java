package com.Codexsystem.Basilico.Basilico.configuration.security;

import com.Codexsystem.Basilico.Basilico.management.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;


@Component
public class TokenConfig {

    private final String secretKey = "mySecretKey12345";
    Algorithm algorithm = Algorithm.HMAC256(secretKey);


    public String generateToken(Usuario usuario) throws Exception {
        return JWT.create()
                .withClaim("username", usuario.getUsername())
                .withSubject(usuario.getSenha())
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JwtDate> validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .build().verify(token);

            return Optional.of(JwtDate.builder()
                    .username(decodedJWT.getClaim("username").asString())
                    .email(decodedJWT.getSubject())
                    .build());
        } catch (JwtException e) {
            return Optional.empty();

        }
    }

}
