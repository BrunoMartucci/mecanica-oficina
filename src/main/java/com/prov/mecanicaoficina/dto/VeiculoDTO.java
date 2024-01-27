package com.prov.mecanicaoficina.dto;

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
