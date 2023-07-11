package com.ecommerce.Service;

import com.ecommerce.api.exception.ClienteNaoEncontradoException;
import com.ecommerce.model.entity.Cliente;
import com.ecommerce.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente obterClientePorId(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));
    }


    public List<Cliente> obterTodosOsClientes() {
        return clienteRepository.findAll();
    }
    public Cliente atualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void removerCliente(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }

}
