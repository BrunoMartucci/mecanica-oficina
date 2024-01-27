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
import java.util.stream.Collectors;

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

        servico.setNome(servicoDTO.getNome());
        servico.setPrecoMaoDeObra(servicoDTO.getPrecoMaoDeObra());

        return (Servico) servicoRepository.save(servico);
    }

    public void deletarServico(Long id) {
        Servico servico = obterServicoPorId(id);
        servicoRepository.delete(servico);
    }

    public List<Servico> listarTodosServicos() {
        return servicoRepository.findAll();
    }

    public List<Servico> listarServicosPorNome(String nome) {
        return servicoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<ServicoDTO> listarServicosEmOrdemAlfabetica(String nomeFiltro) {
        List<Servico> servicos = servicoRepository.findByNomeContainingIgnoreCaseOrderByNome(nomeFiltro);
        return servicos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ServicoDTO convertToDTO(Servico servico) {
        ServicoDTO dto = new ServicoDTO();
        dto.setId(servico.getId());
        dto.setNome(servico.getNome());
        dto.setPrecoMaoDeObra(servico.getPrecoMaoDeObra());
        return dto;
    }
}