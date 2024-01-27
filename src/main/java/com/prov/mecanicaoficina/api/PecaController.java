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

    @PostMapping
    public ResponseEntity<Peca> criarPeca(@RequestBody Peca peca) {
        Peca novaPeca = pecaService.criarPeca(peca);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPeca);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Peca> obterPecaPorId(@PathVariable Long id) {
        Peca peca = pecaService.obterPecaPorId(id);
        return ResponseEntity.ok(peca);
    }

    @GetMapping
    public ResponseEntity<List<Peca>> listarPecas() {
        List<Peca> pecas = pecaService.listarPecas();
        return ResponseEntity.ok(pecas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Peca> atualizarPeca(@PathVariable Long id, @RequestBody Peca pecaAtualizada) {
        Peca pecaAtualizadaNoBanco = pecaService.atualizarPeca(id, pecaAtualizada);
        return ResponseEntity.ok(pecaAtualizadaNoBanco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPeca(@PathVariable Long id) {
        pecaService.deletarPeca(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{pecaId}/entrada/{quantidade}")
    public ResponseEntity<Peca> realizarEntradaEstoque(@PathVariable Long pecaId, @PathVariable int quantidade) {
        Peca pecaAtualizada = pecaService.realizarEntradaEstoque(pecaId, quantidade);
        return ResponseEntity.ok(pecaAtualizada);
    }

    @PostMapping("/{pecaId}/saida/{quantidade}")
    public ResponseEntity<Peca> realizarSaidaEstoque(@PathVariable Long pecaId, @PathVariable int quantidade) {
        Peca pecaAtualizada = pecaService.realizarSaidaEstoque(pecaId, quantidade);
        return ResponseEntity.ok(pecaAtualizada);
    }
}

