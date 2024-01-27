package com.prov.mecanicaoficina.repository;

import com.prov.mecanicaoficina.entity.Peca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PecaRepository extends JpaRepository {
    @Query(value = "SELECT * FROM Peca p WHERE p.nome = ?1", nativeQuery = true)
    List<Peca> findByNome(String nome);
}
