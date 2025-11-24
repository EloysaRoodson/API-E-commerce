package com.eloysaRoodson.API.E_commerce.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    
    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    private String email;
    
    @NotBlank(message = "A senha é obrigatória")
    private String senha;
    
    @NotBlank(message = "O endereço é obrigatório")
    private String endereco;
    
    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;
}