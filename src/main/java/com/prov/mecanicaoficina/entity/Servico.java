package com.prov.mecanicaoficina.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
public class Servico extends PadraoId{
    @NotBlank
    private String nome;

    @NotNull
    private BigDecimal precoMaoDeObra;

    public Servico(String nome, BigDecimal precoMaoDeObra) {
        this.nome = nome;
        this.precoMaoDeObra = precoMaoDeObra;
    }
    public Servico(){
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoMaoDeObra() {
        return precoMaoDeObra;
    }

    public void setPrecoMaoDeObra(BigDecimal precoMaoDeObra) {
        this.precoMaoDeObra = precoMaoDeObra;
    }
}
