package com.ecommerce.Service;

import com.ecommerce.model.entity.Carrinho;
import com.ecommerce.model.entity.Produto;
import com.ecommerce.model.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;

    @Autowired
    public CarrinhoService(CarrinhoRepository carrinhoRepository) {
        this.carrinhoRepository = carrinhoRepository;
    }

    public Carrinho criarCarrinho(Carrinho carrinho) {
        List<Produto> produtos = carrinho.getProdutos();
        double precoTotal = 0.0;

        for (Produto produto : produtos) {
            precoTotal += produto.getPreco();
        }

        carrinho.setPrecoTotal(precoTotal);
        return carrinhoRepository.save(carrinho);
    }

    public double calcularPrecoTotal(Long carrinhoId) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new NoSuchElementException("Carrinho não encontrado"));

        List<Produto> produtos = carrinho.getProdutos();

        double precoTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        BigDecimal precoTotalArredondado = BigDecimal.valueOf(precoTotal)
                .setScale(2, RoundingMode.HALF_UP);

        return precoTotalArredondado.doubleValue();
    }

    public Carrinho obterCarrinhoPorId(Long carrinhoId) {
        return carrinhoRepository.findById(carrinhoId).orElse(null);
    }

    public List<Carrinho> obterTodosOsCarrinhos() {
        return carrinhoRepository.findAll();
    }

    public Carrinho atualizarCarrinho(Carrinho carrinho) {
        return carrinhoRepository.save(carrinho);
    }
    public void removerCarrinho(Long carrinhoId) {
        carrinhoRepository.deleteById(carrinhoId);
    }

}
