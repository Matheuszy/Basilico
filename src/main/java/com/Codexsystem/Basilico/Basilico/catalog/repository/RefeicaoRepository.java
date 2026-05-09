package com.Codexsystem.Basilico.Basilico.catalog.repository;

import com.Codexsystem.Basilico.Basilico.catalog.model.Refeicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {

    Optional<Refeicao> findByNome(String nome);

}
