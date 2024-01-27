package com.prov.mecanicaoficina.dto;

import java.math.BigDecimal;

public class PecaDTO {

    private String nome;
    private String fabricante;
    private Integer quantidadeEstoque;
    private BigDecimal precoUnitario;

    public PecaDTO(String nome, int quantidadeEstoque, BigDecimal precoUnitario) {
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.precoUnitario = precoUnitario;
    }
}
