package com.ecommerce.api.controller;

import com.ecommerce.Service.CarrinhoService;
import com.ecommerce.model.entity.Carrinho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrinhos")
@CrossOrigin
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @Autowired
    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping
    public ResponseEntity<Carrinho> criarCarrinho(@RequestBody Carrinho carrinho) {
        Carrinho novoCarrinho = carrinhoService.criarCarrinho(carrinho);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCarrinho);
    }

    @GetMapping("/{id}/preco-total")
    public ResponseEntity<Double> calcularPrecoTotal(@PathVariable Long id) {
        double precoTotal = carrinhoService.calcularPrecoTotal(id);
        return ResponseEntity.ok(precoTotal);
    }

    @GetMapping("/{carrinhoId}")
    public ResponseEntity<Carrinho> obterCarrinhoPorId(@PathVariable Long carrinhoId) {
        Carrinho carrinho = carrinhoService.obterCarrinhoPorId(carrinhoId);
        if (carrinho != null) {
            return ResponseEntity.ok(carrinho);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Carrinho>> obterTodosOsCarrinhos() {
        List<Carrinho> carrinhos = carrinhoService.obterTodosOsCarrinhos();
        return ResponseEntity.ok(carrinhos);
    }

    @PutMapping("/{carrinhoId}")
    public ResponseEntity<Carrinho> atualizarCarrinho(@PathVariable Long carrinhoId, @RequestBody Carrinho carrinhoAtualizado) {
        Carrinho carrinho = carrinhoService.obterCarrinhoPorId(carrinhoId);
        if (carrinho != null) {
            // Atualize as propriedades relevantes do carrinho existente com base no carrinho atualizado
            carrinho.setCliente(carrinhoAtualizado.getCliente());
            carrinho.setProdutos(carrinhoAtualizado.getProdutos());
            carrinho.atualizarPrecoTotal();

            Carrinho carrinhoAtualizadoObj = carrinhoService.atualizarCarrinho(carrinho); // Chame o método de serviço para atualizar o carrinho

            return ResponseEntity.ok(carrinhoAtualizadoObj);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{carrinhoId}")
    public ResponseEntity<Void> removerCarrinho(@PathVariable Long carrinhoId) {
        carrinhoService.removerCarrinho(carrinhoId);
        return ResponseEntity.noContent().build();
    }

}
