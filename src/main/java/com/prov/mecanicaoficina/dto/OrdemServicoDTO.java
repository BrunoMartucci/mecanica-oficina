package com.prov.mecanicaoficina.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class OrdemServicoDTO {
    @NotNull
    private Long servicoId;
    private Long pecaId;
    private Integer quantidadePeca;
    @NotNull
    private Long veiculoId;
    @NotNull
    private Long clienteId;
    @NotNull
    private LocalDateTime dataInicio;

    public Long getServicoId() {
        return servicoId;
    }

    private LocalDateTime dataFinalizacao;

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public void setServicoId(Long servicoId) {
        this.servicoId = servicoId;
    }

    public Long getPecaId() {
        return pecaId;
    }

    public void setPecaId(Long pecaId) {
        this.pecaId = pecaId;
    }

    public Integer getQuantidadePeca() {
        return quantidadePeca;
    }

    public void setQuantidadePeca(Integer quantidadePeca) {
        this.quantidadePeca = quantidadePeca;
    }

    public Long getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }
}