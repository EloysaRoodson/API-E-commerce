package com.eloysaRoodson.API.E_commerce.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;



@Data

public class OrderDTO {
    @NotEmpty(message = "O pedido deve conter pelo menos um item")
    @Valid
    private List<OrderItemDTO> itens;
}