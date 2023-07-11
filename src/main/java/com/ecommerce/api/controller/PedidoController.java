package com.ecommerce.api.controller;

import com.ecommerce.Service.PedidoService;
import com.ecommerce.model.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.criarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<Pedido> obterPedidoPorId(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.obterPedidoPorId(pedidoId);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> obterTodosOsPedidos() {
        List<Pedido> pedidos = pedidoService.obterTodosOsPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @PutMapping("/{pedidoId}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long pedidoId, @RequestBody Pedido pedidoAtualizado) {
        Pedido pedido = pedidoService.obterPedidoPorId(pedidoId);
        if (pedido != null) {

            pedido.setCliente(pedidoAtualizado.getCliente());
            pedido.setProdutos(pedidoAtualizado.getProdutos());
            pedido.setStatus(pedidoAtualizado.getStatus());

            Pedido pedidoAtualizadoObj = pedidoService.atualizarPedido(pedido);

            return ResponseEntity.ok(pedidoAtualizadoObj);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<Void> removerPedido(@PathVariable Long pedidoId) {
        pedidoService.removerPedido(pedidoId);
        return ResponseEntity.noContent().build();
    }

}

