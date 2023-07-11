package com.ecommerce.Service;

import com.ecommerce.model.entity.Pedido;
import com.ecommerce.model.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido criarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido obterPedidoPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId).orElse(null);
    }

    public List<Pedido> obterTodosOsPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido atualizarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void removerPedido(Long pedidoId) {
        pedidoRepository.deleteById(pedidoId);
    }


}
