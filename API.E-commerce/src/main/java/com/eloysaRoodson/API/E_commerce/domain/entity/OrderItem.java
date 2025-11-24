package com.eloysaRoodson.API.E_commerce.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "item_pedido")
public class OrderItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Order pedido;
    
    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Product produto;
    
    @Column(nullable = false)
    private Integer quantidade;
    
    @Column(nullable = false)
    private BigDecimal preco;
    
    @Column(nullable = false)
    private BigDecimal subtotal;
    
    @PrePersist
    @PreUpdate
    public void calculateSubtotal() {
        if (quantidade != null && preco != null) {
            subtotal = preco.multiply(BigDecimal.valueOf(quantidade));
        }
    }
}