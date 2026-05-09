package com.Codexsystem.Basilico.Basilico.management.repository;

import com.Codexsystem.Basilico.Basilico.management.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("DELETE FROM Usuario u WHERE u.username = :username")
    String deleteByUsername(String username);
}
