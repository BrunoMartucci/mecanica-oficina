package com.prov.mecanicaoficina.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository {
    @Query(value = "SELECT id, nome FROM Cliente c WHERE UPPER(c.nome) LIKE UPPER(?1) ORDER BY c.nome", nativeQuery = true)
    List<Object[]> findByNomeOrderByNome(String nome);

}
