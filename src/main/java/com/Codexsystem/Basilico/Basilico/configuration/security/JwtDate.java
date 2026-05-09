package com.Codexsystem.Basilico.Basilico.configuration.security;

import lombok.Builder;

@Builder
public record JwtDate(
        String username,
        String email
) {
}
