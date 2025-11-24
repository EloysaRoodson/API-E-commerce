package com.eloysaRoodson.API.E_commerce.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

@Data
@RequestMapping("/api/pedidos")
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User usuario;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<OrderItem> itens = new ArrayList<>();
    
    @Column(nullable = false)
    private LocalDateTime dataPedido;
    
    @Column(nullable = false)
    private BigDecimal valorTotal;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;
    
    public enum OrderStatus {
        PENDENTE,
        CONFIRMADO,
        ENVIADO,
        ENTREGUE,
        CANCELADO
    }
    
    @PrePersist
    public void prePersist() {
        dataPedido = LocalDateTime.now();
    }
}