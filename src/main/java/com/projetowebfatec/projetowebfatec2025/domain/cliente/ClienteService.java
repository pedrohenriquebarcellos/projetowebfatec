package com.projetowebfatec.projetowebfatec2025.domain.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetowebfatec.projetowebfatec2025.entities.Cliente;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarCliente() {
        return clienteRepository.findAll();
    }

    public Cliente criarCliente(Cliente cadastarCliente) {
        var cliente = clienteRepository.save(cadastarCliente);
        return cliente;
    }

    public Boolean deletarCliente(Long id) {
        clienteRepository.deleteById(id);

        var clienteDeletado = clienteRepository.findById(id);
        return clienteDeletado.isPresent();
    }

    public boolean atualizarCliente(Long id, Cliente clienteAtualizado) {
        Optional<Cliente> clienteOptional = buscarClientePorId(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setIdade(clienteAtualizado.getIdade());
            cliente.setEndereco(clienteAtualizado.getEndereco());
        }

        return false;
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }
    
}
