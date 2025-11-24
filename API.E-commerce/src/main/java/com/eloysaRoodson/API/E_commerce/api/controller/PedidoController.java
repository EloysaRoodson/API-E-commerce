package com.eloysaRoodson.API.E_commerce.api.controller;

import java.util.List;

import javax.print.PrintService;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PrintService pedidoService;

    @GetMapping
    public <Pedido> List<Pedido> listar() {
        return ((PedidoController) pedidoService).listar();
    }
}
