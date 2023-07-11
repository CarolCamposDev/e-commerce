package com.ecommerce.Service;

import com.ecommerce.model.entity.Produto;
import com.ecommerce.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto obterProdutoPorId(Long produtoId) {
        return produtoRepository.findById(produtoId).orElse(null);
    }

    public List<Produto> obterTodosOsProdutos() {
        return produtoRepository.findAll();
    }

    public Produto atualizarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void removerProduto(Long produtoId) {
        produtoRepository.deleteById(produtoId);
    }


}
