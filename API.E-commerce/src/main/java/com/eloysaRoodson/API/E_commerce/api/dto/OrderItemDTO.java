package com.eloysaRoodson.API.E_commerce.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderItemDTO {
    @NotNull(message = "O ID do produto é obrigatório")
    private Long idDoProduto;
    
    @NotNull(message = "A quantidade é obrigatória")
    @Positive(message = "A quantidade deve ser maior que zero")
    private Integer quantidade;
}