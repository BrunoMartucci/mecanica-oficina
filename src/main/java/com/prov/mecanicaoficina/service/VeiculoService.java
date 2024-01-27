package com.prov.mecanicaoficina.service;

import com.prov.mecanicaoficina.dto.VeiculoDTO;
import com.prov.mecanicaoficina.entity.Veiculo;
import com.prov.mecanicaoficina.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    public Veiculo obterVeiculoPorId(Long id) {
        Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);

        if (optionalVeiculo.isPresent()) {
            return optionalVeiculo.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não encontrado com o ID: " + id);
        }
    }

    public Veiculo criarVeiculo(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setAnoFabricacao(veiculoDTO.getAnoFabricacao());
        veiculo.setKmRodados(veiculoDTO.getKmRodados());

        return (Veiculo) veiculoRepository.save(veiculo);
    }

    public Veiculo atualizarVeiculo(Long id, VeiculoDTO veiculoDTO) {
        Veiculo veiculo = obterVeiculoPorId(id);

        // Atualizar as propriedades de veiculo com base nas propriedades de veiculoDTO
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setAnoFabricacao(veiculoDTO.getAnoFabricacao());
        veiculo.setKmRodados(veiculoDTO.getKmRodados());

        return (Veiculo) veiculoRepository.save(veiculo);
    }

    public void deletarVeiculo(Long id) {
        Veiculo veiculo = obterVeiculoPorId(id);
        veiculoRepository.delete(veiculo);
    }
}
