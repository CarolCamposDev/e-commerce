package com.ecommerce.api.controller;

import com.ecommerce.Service.ClienteService;
import com.ecommerce.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin
class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.criarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> obterClientePorId(@PathVariable Long clienteId) {
        Cliente cliente = clienteService.obterClientePorId(clienteId);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obterTodosOsClientes() {
        List<Cliente> clientes = clienteService.obterTodosOsClientes();
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long clienteId, @RequestBody Cliente clienteAtualizado) {
        Cliente cliente = clienteService.obterClientePorId(clienteId);
        if (cliente != null) {
            // Atualize as propriedades relevantes do cliente existente com base no cliente atualizado
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setCpf(clienteAtualizado.getCpf());
            cliente.setEmail(clienteAtualizado.getEmail());
            cliente.setSenha(clienteAtualizado.getSenha());
            cliente.setEndereco(clienteAtualizado.getEndereco());

            Cliente clienteAtualizadoObj = clienteService.atualizarCliente(cliente); // Chame o método de serviço para atualizar o cliente

            return ResponseEntity.ok(clienteAtualizadoObj);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> removerCliente(@PathVariable Long clienteId) {
        clienteService.removerCliente(clienteId);
        return ResponseEntity.noContent().build();
    }
}

