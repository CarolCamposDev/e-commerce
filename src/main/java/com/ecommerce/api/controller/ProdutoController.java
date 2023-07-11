package com.ecommerce.api.controller;

import com.ecommerce.Service.ProdutoService;
import com.ecommerce.model.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.criarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<Produto> obterProdutoPorId(@PathVariable Long produtoId) {
        Produto produto = produtoService.obterProdutoPorId(produtoId);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Produto>> obterTodosOsProdutos() {
        List<Produto> produtos = produtoService.obterTodosOsProdutos();
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long produtoId, @RequestBody Produto produtoAtualizado) {
        Produto produto = produtoService.obterProdutoPorId(produtoId);
        if (produto != null) {
            produto.setNome(produtoAtualizado.getNome());
            produto.setDescricao(produtoAtualizado.getDescricao());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());

            Produto produtoAtualizadoObj = produtoService.atualizarProduto(produto);

            return ResponseEntity.ok(produtoAtualizadoObj);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Void> removerProduto(@PathVariable Long produtoId) {
        produtoService.removerProduto(produtoId);
        return ResponseEntity.noContent().build();
    }


}
