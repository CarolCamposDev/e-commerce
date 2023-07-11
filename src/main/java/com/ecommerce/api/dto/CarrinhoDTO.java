package com.ecommerce.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoDTO {

    private Long id;
    private ClienteDTO usuario;
    private List<ProdutoDTO> produtos;

    private String status;

}
