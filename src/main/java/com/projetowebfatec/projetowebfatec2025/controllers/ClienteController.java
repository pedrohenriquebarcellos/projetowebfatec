package com.projetowebfatec.projetowebfatec2025.controllers;

import org.springframework.web.bind.annotation.*;

import com.projetowebfatec.projetowebfatec2025.entities.Cliente;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Cliente> CriarCliente(@RequestBody Cliente cliente) {
        cliente.setId(idCount++);
        clientes.add(cliente);

        logger.info("Recebido JSON: Nome={}, Idade={}", cliente.getNome(), cliente.getIdade());
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @DeleteMapping("/deletarCliente/{id}")
    public String DeletarCliente(@PathVariable Long id) {
        clientes.removeIf(clientes -> clientes.getId().equals(id));
        return "Cliente id:" + id + " removido com sucesso";
    }

    @PutMapping("/atualizarCliente/{id}")
    public ResponseEntity<Cliente> AtualizarCliente(@RequestBody Cliente clienteAtualizado) {
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

                return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(clienteAtualizado, HttpStatus.UPGRADE_REQUIRED);
    }

    @GetMapping("/listarClientes")
    public List<Cliente> ListarClientes() {
        return clientes;
    }

    @GetMapping("/buscarCliente/{id}")
     public ResponseEntity<?> buscarClientePorId(@PathVariable Long id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return ResponseEntity.ok(cliente);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente com ID " + id + " não encontrado.");
    }
}