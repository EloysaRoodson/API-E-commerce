package com.eloysaRoodson.API.E_commerce.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @Column(nullable = false)
    private BigDecimal preco;
    
    @Column(nullable = false)
    private Integer quantidadeEstoque;
    
    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Category categoria;
    
    private String urlImagem;

   public String getUrlImagem() {
       return urlImagem;
   }

   public void setUrlImagem(String urlImagem) {
       this.urlImagem = urlImagem;
   }

   public Integer getQuantidadeEstoque() {
       return quantidadeEstoque;
   }

   public void setQuantidadeEstoque(Integer quantidadeEstoque) {
       this.quantidadeEstoque = quantidadeEstoque;
   }

   
}