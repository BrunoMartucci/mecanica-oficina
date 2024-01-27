package com.prov.mecanicaoficina.service;

import com.prov.mecanicaoficina.dto.PecaDTO;
import com.prov.mecanicaoficina.entity.Peca;
import com.prov.mecanicaoficina.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PecaService {

    @Autowired
    private PecaRepository pecaRepository;

    public Peca criarPeca(Peca peca) {
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

        return (Peca) pecaRepository.save(pecaExistente);
    }
    public void deletarPeca(Long pecaId) {
        Peca peca = obterPecaPorId(pecaId);

        if (peca.getQuantidadeEstoque() != 0) {
            throw new IllegalStateException("Não é possível excluir a peça, pois ela já teve movimentação no estoque.");
        }

        pecaRepository.delete(peca);
    }
    public Peca realizarEntradaEstoque(Long pecaId, int quantidade) {
        Peca peca = obterPecaPorId(pecaId);

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de entrada deve ser maior que zero.");
        }

        peca.setQuantidadeEstoque(peca.getQuantidadeEstoque() + quantidade);

        return (Peca) pecaRepository.save(peca);
    }

    public Peca realizarSaidaEstoque(Long pecaId, int quantidade) {
        Peca peca = obterPecaPorId(pecaId);

        if (quantidade > peca.getQuantidadeEstoque()) {
            throw new IllegalArgumentException("Estoque insuficiente para realizar a saída.");
        }

        peca.setQuantidadeEstoque(peca.getQuantidadeEstoque() - quantidade);

        return (Peca) pecaRepository.save(peca);
    }

    public List<PecaDTO> listarPecasPorNome(String nome) {
        List<Peca> pecas = pecaRepository.findByNome(nome);
        return convertToDTOList(pecas);

        private List<PecaDTO> convertToDTOList(List<Peca> pecas) {
            return pecas.stream()
                    .map(peca -> new PecaDTO(peca.geti(), peca.getNome(), peca.getQuantidadeEstoque(), peca.getPrecoUnitario()))
                    .collect(Collectors.toList());
        }
    }
}


