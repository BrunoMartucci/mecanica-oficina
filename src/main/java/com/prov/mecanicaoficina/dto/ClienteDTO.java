package com.prov.mecanicaoficina.dto;

import jakarta.validation.constraints.NotBlank;

public class ClienteDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;
    public ClienteDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
