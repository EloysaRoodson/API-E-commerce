package com.eloysaRoodson.API.E_commerce.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    
    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;

    private String descricao;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "A quantidade em estoque é obrigatória")
    @PositiveOrZero(message = "A quantidade em estoque não pode ser negativa")
    private Integer quantidadeEmEstoque;

    @NotNull(message = "A categoria é obrigatória")
    private Long idCategoria;

    private String urlImagem;
}