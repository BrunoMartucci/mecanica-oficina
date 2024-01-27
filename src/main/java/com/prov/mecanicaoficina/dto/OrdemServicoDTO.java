package com.prov.mecanicaoficina.dto;

import com.prov.mecanicaoficina.entity.OrdemDeServico;

import java.time.LocalDateTime;

public class OrdemServicoDTO {
    private Long servicoId;
    private Long pecaId;
    private Integer quantidadePeca;
    private Long veiculoId;
    private Long clienteId;
    private LocalDateTime dataInicio;

}