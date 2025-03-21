package com.projetowebfatec.projetowebfatec2025.controllers;

import org.springframework.web.bind.annotation.*;

import com.projetowebfatec.projetowebfatec2025.domain.cliente.ClienteService;
import com.projetowebfatec.projetowebfatec2025.entities.Cliente;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class.getName());

    private final List<Cliente> clientes = new ArrayList<>();

    //http://localhost:8080/api/cliente/criarCliente => POST
    @PostMapping("/criarCliente")
    public ResponseEntity<Cliente> CriarCliente(@RequestBody Cliente cliente) {
        var novoCliente = clienteService.criarCliente(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.OK);
    }

    @DeleteMapping("/deletarCliente/{id}")
    public ResponseEntity<?> DeletarCliente(@PathVariable Long id) {
        boolean clienteDeletado = clienteService.deletarCliente(id);
        
        if (clienteDeletado) {
            return new ResponseEntity<>(clienteDeletado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(clienteDeletado, HttpStatus.NOT_FOUND);
        }
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
        return clienteService.listarCliente();
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