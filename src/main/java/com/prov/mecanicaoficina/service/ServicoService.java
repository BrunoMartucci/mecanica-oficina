package com.prov.mecanicaoficina.service;

import com.prov.mecanicaoficina.dto.ServicoDTO;
import com.prov.mecanicaoficina.entity.Servico;
import com.prov.mecanicaoficina.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    public Servico obterServicoPorId(Long id) {
        Optional<Servico> optionalServico = servicoRepository.findById(id);

        if (optionalServico.isPresent()) {
            return optionalServico.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico não encontrado com o ID: " + id);
        }
    }
    public Servico criarServico(ServicoDTO servicoDTO) {
        Servico servico = new Servico();
        servico.setNome(servicoDTO.getNome());
        servico.setPrecoMaoDeObra(servicoDTO.getPrecoMaoDeObra());

        return (Servico) servicoRepository.save(servico);
    }

    public Servico atualizarServico(Long id, ServicoDTO servicoDTO) {
        Servico servico = obterServicoPorId(id);

        // Atualizar as propriedades de servico com base nas propriedades de servicoDTO
        servico.setNome(servicoDTO.getNome());
        servico.setPrecoMaoDeObra(servicoDTO.getPrecoMaoDeObra());

        return (Servico) servicoRepository.save(servico);
    }

    public void deletarServico(Long id) {
        Servico servico = obterServicoPorId(id);
        servicoRepository.delete(servico);
    }
}