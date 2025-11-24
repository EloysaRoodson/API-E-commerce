package com.eloysaRoodson.API.E_commerce.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    
    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nome;
    
    private String descricao;
}