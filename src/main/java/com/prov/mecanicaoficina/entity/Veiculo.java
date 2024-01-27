package com.prov.mecanicaoficina.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Veiculo extends  PadraoId{

    @NotBlank
    private String modelo;

    @NotBlank
    private String marca;

    @NotBlank
    private String anoFabricacao;

    @NotNull
    private Integer kmRodados;

    @OneToMany(mappedBy = "cliente")
    private List<Veiculo> veiculos;

    public Veiculo(String modelo, String marca, String anoFabricacao, Integer kmRodados, List<Veiculo> veiculos) {
        this.modelo = modelo;
        this.marca = marca;
        this.anoFabricacao = anoFabricacao;
        this.kmRodados = kmRodados;
        this.veiculos = veiculos;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(String anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Integer getKmRodados() {
        return kmRodados;
    }

    public void setKmRodados(Integer kmRodados) {
        this.kmRodados = kmRodados;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
}



