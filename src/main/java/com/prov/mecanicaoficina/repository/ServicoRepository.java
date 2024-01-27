package com.prov.mecanicaoficina.repository;

import com.prov.mecanicaoficina.entity.Peca;
import com.prov.mecanicaoficina.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository {

    List<Servico> findByNomeContainingIgnoreCase(String nome);
    List<Peca> findByNomeContainingIgnoreCaseOrderByNome(String nome);
}
