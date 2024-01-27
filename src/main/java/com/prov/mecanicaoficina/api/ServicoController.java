package com.prov.mecanicaoficina.api;

import com.prov.mecanicaoficina.dto.ServicoDTO;
import com.prov.mecanicaoficina.entity.Servico;
import com.prov.mecanicaoficina.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/ordenados-por-nome")
    public ResponseEntity<List<ServicoDTO>> listarServicosOrdenadosPorNome(@RequestParam(required = false) String nome) {
        List<Servico> servicos;

        if (nome != null && !nome.isEmpty()) {
            servicos = servicoService.listarServicosPorNome(nome);
        } else {
            servicos = servicoService.listarTodosServicos();
        }

        List<ServicoDTO> servicosDTO = servicos.stream()
                .sorted(Comparator.comparing(Servico::getNome))
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(servicosDTO);
    }

    private ServicoDTO convertToDTO(Servico servico) {
        ServicoDTO dto = new ServicoDTO();
        dto.setId(servico.getId());
        dto.setNome(servico.getNome());
        dto.setPrecoMaoDeObra(servico.getPrecoMaoDeObra());
        return dto;
    }
}


