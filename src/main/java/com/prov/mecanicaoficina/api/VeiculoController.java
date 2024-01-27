package com.prov.mecanicaoficina.api;

import com.prov.mecanicaoficina.dto.VeiculoDTO;
import com.prov.mecanicaoficina.entity.Veiculo;
import com.prov.mecanicaoficina.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<List<Veiculo>> listarVeiculos() {
        List<Veiculo> veiculos = veiculoService.listarVeiculos();
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> obterVeiculo(@PathVariable Long id) {
        Veiculo veiculo = veiculoService.obterVeiculoPorId(id);
        return ResponseEntity.ok(veiculo);
    }

    @PostMapping
    public ResponseEntity<Veiculo> criarVeiculo(@RequestBody VeiculoDTO veiculoDTO) {
        Veiculo novoVeiculo = veiculoService.criarVeiculo(veiculoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Long id, @RequestBody VeiculoDTO veiculoDTO) {
        Veiculo veiculoAtualizado = veiculoService.atualizarVeiculo(id, veiculoDTO);
        return ResponseEntity.ok(veiculoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Long id) {
        veiculoService.deletarVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}
