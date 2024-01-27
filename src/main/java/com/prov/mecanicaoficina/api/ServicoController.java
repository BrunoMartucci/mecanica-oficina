package com.prov.mecanicaoficina.api;

import com.prov.mecanicaoficina.dto.ServicoDTO;
import com.prov.mecanicaoficina.entity.Servico;
import com.prov.mecanicaoficina.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public ResponseEntity<List<Servico>> listarServicos() {
        List<Servico> servicos = servicoService.listarServicos();
        return ResponseEntity.ok(servicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> obterServico(@PathVariable Long id) {
        Servico servico = servicoService.obterServicoPorId(id);
        return ResponseEntity.ok(servico);
    }

    @PostMapping
    public ResponseEntity<Servico> criarServico(@RequestBody ServicoDTO servicoDTO) {
        Servico novoServico = servicoService.criarServico(servicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoServico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizarServico(@PathVariable Long id, @RequestBody ServicoDTO servicoDTO) {
        Servico servicoAtualizado = servicoService.atualizarServico(id, servicoDTO);
        return ResponseEntity.ok(servicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServico(@PathVariable Long id) {
        servicoService.deletarServico(id);
        return ResponseEntity.noContent().build();
    }
}

