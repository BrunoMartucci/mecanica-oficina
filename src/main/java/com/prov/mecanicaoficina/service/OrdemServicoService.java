package com.prov.mecanicaoficina.service;

import com.prov.mecanicaoficina.dto.OrdemServicoDTO;
import com.prov.mecanicaoficina.entity.*;
import com.prov.mecanicaoficina.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoService {

    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    @Autowired
    private PecaRepository pecaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private ServicoService servicoService;
    @Autowired
    private PecaService pecaService;
    @Autowired
    private VeiculoService veiculoService;
    @Autowired
    private ClienteService clienteService;

    public List<OrdemServico> listarOrdensServico() {
        return ordemServicoRepository.findAll();
    }

    public OrdemServico abrirOrdemServico(OrdemServicoDTO ordemServicoDTO) {
        validarIds(ordemServicoDTO.getServicoId(), ordemServicoDTO.getPecaId(), ordemServicoDTO.getVeiculoId(), ordemServicoDTO.getClienteId());

        Servico servico = obterServicoPorId(ordemServicoDTO.getServicoId());
        Peca peca = obterPecaPorId(ordemServicoDTO.getPecaId());
        Veiculo veiculo = obterVeiculoPorId(ordemServicoDTO.getVeiculoId());
        Cliente cliente = obterClientePorId(ordemServicoDTO.getClienteId());

        OrdemServico novaOrdemServico = new OrdemServico();
        novaOrdemServico.setServico(servico);
        novaOrdemServico.setPeca(peca);
        novaOrdemServico.setVeiculo(veiculo);
        novaOrdemServico.setCliente(cliente);
        novaOrdemServico.setDataInicio(ordemServicoDTO.getDataInicio());

        return (OrdemServico) ordemServicoRepository.save(novaOrdemServico);
    }

    private void validarIds(Long idServico, Long idPeca, Long idVeiculo, Long idCliente) {
        if (idServico == null || idPeca == null || idVeiculo == null || idCliente == null) {
            throw new IllegalArgumentException("IDs de serviço, peça, veículo e cliente são obrigatórios.");
        }

    }

    private Servico obterServicoPorId(Long idServico) {
        return servicoRepository.findById(idServico)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado com o ID: " + idServico));
    }

    private Peca obterPecaPorId(Long idPeca) {
        return pecaRepository.findById(idPeca)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Peça não encontrada com o ID: " + idPeca));
    }

    private Veiculo obterVeiculoPorId(Long idVeiculo) {
        return veiculoRepository.findById(idVeiculo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado com o ID: " + idVeiculo));
    }

    private Cliente obterClientePorId(Long idCliente) {
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado com o ID: " + idCliente));
    }

    public OrdemServico finalizarOrdemServico(Long id, LocalDateTime dataFinalizacao) {
        OrdemServico ordemServico = obterOrdemServicoPorId(id);

        if (ordemServico.getDataFinalizacao() != null) {
            throw new IllegalStateException("A ordem de serviço já foi finalizada.");
        }

        ordemServico.setDataFinalizacao(dataFinalizacao);

        validarEstoqueDePecas(ordemServico);

        return (OrdemServico) ordemServicoRepository.save(ordemServico);
    }

    private void validarEstoqueDePecas(OrdemServico ordemServico) {
        if (ordemServico.getPeca() != null) {
            Peca peca = ordemServico.getPeca();
            int quantidadePecaUtilizada = ordemServico.getQuantidadePeca();

            if (quantidadePecaUtilizada > peca.getQuantidadeEstoque()) {
                throw new IllegalStateException("Estoque insuficiente para a peça utilizada na ordem de serviço.");
            }

            peca.setQuantidadeEstoque(peca.getQuantidadeEstoque() - quantidadePecaUtilizada);
            pecaRepository.save(peca);
        }
    }


    public OrdemServico obterOrdemServicoPorId(Long id) {
        Optional<OrdemServico> optionalOrdemServico = ordemServicoRepository.findById(id);

        if (optionalOrdemServico.isPresent()) {
            return optionalOrdemServico.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem de Serviço não encontrada com o ID: " + id);
        }
    }


    public OrdemServico atualizarOrdemServico(Long id, OrdemServicoDTO ordemServicoDTO) {
        OrdemServico ordemServicoExistente = obterOrdemServicoPorId(id);

        Servico servico = servicoService.obterServicoPorId(ordemServicoDTO.getServicoId());
        ordemServicoExistente.setServico(servico);

        if (ordemServicoDTO.getPecaId() != null) {
            Peca peca = pecaService.obterPecaPorId(ordemServicoDTO.getPecaId());
            ordemServicoExistente.setPeca(peca);
            ordemServicoExistente.setQuantidadePeca(ordemServicoDTO.getQuantidadePeca());
        }

        Veiculo veiculo = veiculoService.obterVeiculoPorId(ordemServicoDTO.getVeiculoId());
        ordemServicoExistente.setVeiculo(veiculo);

        Cliente cliente = clienteService.obterClientePorId(ordemServicoDTO.getClienteId());
        ordemServicoExistente.setCliente(cliente);

        ordemServicoExistente.setDataInicio(ordemServicoDTO.getDataInicio());
        ordemServicoExistente.setDataFinalizacao(ordemServicoDTO.getDataFinalizacao());

        return (OrdemServico) ordemServicoRepository.save(ordemServicoExistente);
    }

    public void excluirOrdemServico(Long id) {
        OrdemServico ordemServico = obterOrdemServicoPorId(id);

        LocalDateTime dataAtual = LocalDateTime.now();

        LocalDateTime dataLimite = dataAtual.minusYears(2);

        if (ordemServico.getDataInicio().isAfter(dataLimite)) {
            ordemServicoRepository.delete(ordemServico);
        } else {
            throw new IllegalStateException("A OrdemServico está fora do período permitido para exclusão.");
        }
    }
}
