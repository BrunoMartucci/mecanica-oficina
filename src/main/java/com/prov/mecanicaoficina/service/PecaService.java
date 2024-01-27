package com.prov.mecanicaoficina.service;

import com.prov.mecanicaoficina.entity.Peca;
import com.prov.mecanicaoficina.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PecaService {

    @Autowired
    private PecaRepository pecaRepository;

    public Peca criarPeca(Peca peca) {
        // Lógica de validação, se necessário
        return (Peca) pecaRepository.save(peca);
    }

    public Peca obterPecaPorId(Long id) {
        Optional<Peca> pecaOptional = pecaRepository.findById(id);

        if (pecaOptional.isPresent()) {
            return pecaOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Peca não encontrada com o ID: " + id);
        }
    }
    public List<Peca> listarPecas() {
        return pecaRepository.findAll();
    }

    public Peca atualizarPeca(Long id, Peca pecaAtualizada) {
        Peca pecaExistente = obterPecaPorId(id);

        // Atualizar as propriedades da pecaExistente com base nas propriedades de pecaAtualizada

        return (Peca) pecaRepository.save(pecaExistente);
    }

    public void deletarPeca(Long id) {
        Peca peca = obterPecaPorId(id);
        pecaRepository.delete(peca);
    }
}
