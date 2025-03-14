package com.projetowebfatec.projetowebfatec2025.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetowebfatec.projetowebfatec2025.entities.Cliente;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class.getName());

    private final List<Cliente> clientes = new ArrayList<>();
    private Long idCount = 1L;

    //http://localhost:8080/api/cliente/criarCliente => POST
    @PostMapping("/criarCliente")
    public String CriarCliente(@RequestBody Cliente cliente) {
        cliente.setId(idCount++);
        clientes.add(cliente);

        logger.info("Recebido JSON: Nome={}, Idade={}", cliente.getNome(), cliente.getIdade());
        return "O Cliente "+cliente.getNome()+ " de idade "+cliente.getIdade()+" foi criado";
    }

    @DeleteMapping("/deletarCliente/{id}")
    public String DeletarCliente(@PathVariable Long id) {
        clientes.removeIf(clientes -> clientes.getId().equals(id));
        return "Cliente id:" + id + " removido com sucesso";
    }

    @PutMapping("/atualizarCliente/{id}")
    public String AtualizarCliente(@RequestBody Cliente clienteAtualizado) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(clienteAtualizado.getId())) {

                if (clienteAtualizado.getNome() != "") {
                    cliente.setNome(clienteAtualizado.getNome());
                }
                
                if (clienteAtualizado.getIdade() != null) {
                    cliente.setIdade(clienteAtualizado.getIdade());
                }
                
                if (clienteAtualizado.getEndereco() != "") {
                    cliente.setEndereco(clienteAtualizado.getEndereco());
                }

                return "Ciente id:" + clienteAtualizado.getId() + " foi atualizado com sucesso";
            }
        }

        return "Cliente não encontrado";
    }

    @GetMapping("/listarClientes")
    public List<Cliente> ListarClientes() {
        return clientes;
    }
}