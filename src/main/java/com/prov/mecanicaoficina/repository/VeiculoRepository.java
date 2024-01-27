package com.prov.mecanicaoficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository {
    @Query(value = "SELECT v.id, v.modelo, v.marca, v.cliente_id, c.nome " +
            "FROM veiculo v " +
            "JOIN cliente c ON v.cliente_id = c.id " +
            "WHERE UPPER(v.modelo) LIKE UPPER(?1) " +
            "ORDER BY v.modelo", nativeQuery = true)
    List<Object[]> findByModeloOrderByModelo(String modelo);
}
