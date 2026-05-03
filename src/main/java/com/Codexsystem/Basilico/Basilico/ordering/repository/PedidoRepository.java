package com.Codexsystem.Basilico.Basilico.ordering.repository;

import com.Codexsystem.Basilico.Basilico.ordering.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.refeicoes LEFT JOIN FETCH p.bebidas WHERE p.id = :id")
    Optional<Pedido> findPedidoCompletoPorId(Long id);
}
