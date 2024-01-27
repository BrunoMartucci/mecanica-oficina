package com.prov.mecanicaoficina.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class Peca extends PadraoId {
    @NotBlank
    private String nome;

    @NotBlank
    private String fabricante;

    @NotNull
    private Integer quantidadeEstoque;

    @NotNull
    private BigDecimal precoUnitario;

}
