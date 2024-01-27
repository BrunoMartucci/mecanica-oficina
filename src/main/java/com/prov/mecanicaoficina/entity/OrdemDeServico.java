package com.prov.mecanicaoficina.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class OrdemDeServico extends PadraoId {


    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "peca_id")
    private Peca peca;

    private Integer quantidadePeca;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @NotNull
    private LocalDateTime dataInicio;

    private LocalDateTime dataFinalizacao;

    // Getters and Setters
}




