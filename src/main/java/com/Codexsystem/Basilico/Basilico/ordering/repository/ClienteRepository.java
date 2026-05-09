package com.Codexsystem.Basilico.Basilico.ordering.repository;

import com.Codexsystem.Basilico.Basilico.ordering.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByEmail(String email);

}
