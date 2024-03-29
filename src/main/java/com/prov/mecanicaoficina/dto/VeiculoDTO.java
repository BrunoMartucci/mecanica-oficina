package com.prov.mecanicaoficina.dto;

import com.prov.mecanicaoficina.entity.Cliente;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VeiculoDTO {

        @NotBlank
        private String modelo;

        @NotBlank
        private String marca;

        @NotNull
        private String anoFabricacao;

        @NotNull
        private Integer kmRodados;


    public VeiculoDTO(Long id, String modelo, String marca, Long clienteId, String nomeCliente) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.clienteId = clienteId;
        this.nomeCliente = nomeCliente;
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
    
}
