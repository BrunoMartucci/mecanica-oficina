package com.prov.mecanicaoficina.service;

import com.prov.mecanicaoficina.dto.ClienteDTO;
import com.prov.mecanicaoficina.entity.Cliente;
import com.prov.mecanicaoficina.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obterClientePorId(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isPresent()) {
            return optionalCliente.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado com o ID: " + id);
        }
    }

    public Cliente criarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());

        return (Cliente) clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = obterClientePorId(id);

        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());

        return (Cliente) clienteRepository.save(cliente);
    }

    public void deletarCliente(Long id) {
        Cliente cliente = obterClientePorId(id);
        clienteRepository.delete(cliente);
    }

    public List<ClienteDTO> listarClientesPorNome(String nome) {
        List<Object[]> clientes = clienteRepository.findByNomeOrderByNome("%" + nome + "%");
        return convertToDTOList(clientes);
    }

    private List<ClienteDTO> convertToDTOList(List<Object[]> clientes) {
        return clientes.stream()
                .map(cliente -> new ClienteDTO((Long) cliente[0], (String) cliente[1]))
                .collect(Collectors.toList());
    }
}

