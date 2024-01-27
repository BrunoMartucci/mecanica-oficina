package com.prov.mecanicaoficina.repository;

import com.prov.mecanicaoficina.entity.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemServicoRepository extends JpaRepository {

    @Query(value = "SELECT * FROM ordem_servico WHERE data_finalizacao IS NULL", nativeQuery = true)
    List<OrdemServico> findOrdensServicoAbertas();

    @Query("SELECT os FROM OrdemServico os WHERE os.dataFinalizacao IS NOT NULL")
    List<OrdemServico> findOrdensServicoFinalizadas();

    @Query(nativeQuery = true, value = "SELECT * FROM ordem_servico os " +
            "JOIN cliente c ON os.cliente_id = c.id " +
            "WHERE c.nome LIKE %:nomeCliente% " +
            "ORDER BY os.data_inicio")
    List<OrdemServico> findByClienteNomeContainingOrderByDataInicio(@Param("nomeCliente") String nomeCliente);
}
