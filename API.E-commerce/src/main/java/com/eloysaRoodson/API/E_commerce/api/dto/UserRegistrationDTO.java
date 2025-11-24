package com.eloysaRoodson.API.E_commerce.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegistrationDTO {

    @NotBlank(message = "Campo Obrigatório")
    private String nome;
    
    @NotBlank(message = "Campo Obrigatório")
    @Email(message = "O e-mail deve ser válido")
    private String email;
    
    @NotBlank(message = "Campo Obrigatório")
    private String senha;
    
    @NotBlank(message = "Campo Obrigatório")
    private String endereco;
    
    @NotBlank(message = "Campo Obrigatório")
    private String telefone;
}