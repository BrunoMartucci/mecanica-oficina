package com.prov.mecanicaoficina.api;

import com.prov.mecanicaoficina.entity.Peca;
import com.prov.mecanicaoficina.service.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pecas")
public class PecaController {

    @Autowired
    private PecaService pecaService;

    @GetMapping
    public ResponseEntity<List<Peca>> listarPecas() {
        List<Peca> pecas = pecaService.listarPecas();
        return ResponseEntity.ok(pecas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Peca> obterPeca(@PathVariable Long id) {
        Peca peca = pecaService.obterPecaPorId(id);
        return ResponseEntity.ok(peca);
    }

    @PostMapping
    public ResponseEntity<Peca> criarPeca(@RequestBody Peca peca) {
        Peca novaPeca = pecaService.criarPeca(peca);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPeca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Peca> atualizarPeca(@PathVariable Long id, @RequestBody Peca pecaAtualizada) {
        Peca pecaAtualizadaResult = pecaService.atualizarPeca(id, pecaAtualizada);
        return ResponseEntity.ok(pecaAtualizadaResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPeca(@PathVariable Long id) {
        pecaService.deletarPeca(id);
        return ResponseEntity.noContent().build();
    }
}

