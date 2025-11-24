package com.eloysaRoodson.API.E_commerce.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String senha;
    
    @Column(nullable = false)
    private String endereco;
    
    @Column(nullable = false)
    private String telefone;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Order> pedidos = new ArrayList<>();
}