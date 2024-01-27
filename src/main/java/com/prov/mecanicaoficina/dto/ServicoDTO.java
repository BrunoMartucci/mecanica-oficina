package com.prov.mecanicaoficina.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public class ServicoDTO {

        @NotBlank
        private String nome;

        @NotNull
        private BigDecimal precoMaoDeObra;


        public String getNome() {
                return nome;
        }
        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
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
