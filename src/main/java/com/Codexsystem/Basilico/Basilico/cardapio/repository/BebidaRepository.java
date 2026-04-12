package com.Codexsystem.Basilico.Basilico.cardapio.repository;

import com.Codexsystem.Basilico.Basilico.cardapio.model.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BebidaRepository extends JpaRepository<Bebida, Long> {

    Optional<Bebida> findBebidaByNome(String nome);
}
