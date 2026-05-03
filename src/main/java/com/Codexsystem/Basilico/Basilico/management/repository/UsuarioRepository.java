package com.Codexsystem.Basilico.Basilico.management.repository;

import com.Codexsystem.Basilico.Basilico.management.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByUsername(String username);

    String deleteByUsername(String username);
}
