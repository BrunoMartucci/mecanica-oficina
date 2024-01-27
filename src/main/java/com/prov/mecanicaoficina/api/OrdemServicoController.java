package com.prov.mecanicaoficina.api;

import com.prov.mecanicaoficina.dto.OrdemServicoDTO;
import com.prov.mecanicaoficina.entity.OrdemServico;
import com.prov.mecanicaoficina.service.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @GetMapping
    public ResponseEntity<List<OrdemServico>> listarOrdensServico() {
        List<OrdemServico> ordensServico = ordemServicoService.listarOrdensServico();
        return ResponseEntity.ok(ordensServico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> obterOrdemServico(@PathVariable Long id) {
        OrdemServico ordemServico = ordemServicoService.obterOrdemServicoPorId(id);
        return ResponseEntity.ok(ordemServico);
    }

    @PostMapping
    public ResponseEntity<OrdemServico> abrirOrdemServico(@RequestBody OrdemServicoDTO ordemServicoDTO) {
        OrdemServico novaOrdemServico = ordemServicoService.abrirOrdemServico(ordemServicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaOrdemServico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdemServico> atualizarOrdemServico(@PathVariable Long id, @RequestBody OrdemServicoDTO ordemServicoDTO) {
        OrdemServico ordemServicoAtualizada = ordemServicoService.atualizarOrdemServico(id, ordemServicoDTO);
        return ResponseEntity.ok(ordemServicoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirOrdemServico(@PathVariable Long id) {
        ordemServicoService.excluirOrdemServico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/abertas")
    public ResponseEntity<List<OrdemServicoDTO>> listarOrdensServicoAbertas() {
        List<OrdemServicoDTO> ordensServicoAbertas = ordemServicoService.listarOrdensServicoAbertas();
        return ResponseEntity.ok(ordensServicoAbertas);
    }


    @GetMapping("/ordens-servico/finalizadas")
    public ResponseEntity<List<OrdemServicoDTO>> listarOrdensServicoFinalizadas() {
        List<OrdemServicoDTO> ordensServicoFinalizadas = ordemServicoService.listarOrdensServicoFinalizadas();
        return ResponseEntity.ok(ordensServicoFinalizadas);
    }

    @GetMapping("/ordens-servico/filtradas")
    public ResponseEntity<List<OrdemServicoDTO>> listarOrdensServicoFiltradas(@RequestParam String nomeCliente) {
        List<OrdemServicoDTO> ordensServicoFiltradas = ordemServicoService.listarOrdensServicoFiltradas(nomeCliente);
        return ResponseEntity.ok(ordensServicoFiltradas);
    }

}