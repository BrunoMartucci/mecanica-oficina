package com.prov.mecanicaoficina.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public class PadraoId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private LocalDateTime dataCadastro = LocalDateTime.now();
}



