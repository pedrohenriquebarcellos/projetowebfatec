package com.projetowebfatec.projetowebfatec2025.domain.cliente;

import java.util.List;

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
}
