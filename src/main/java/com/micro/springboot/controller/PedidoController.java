package com.micro.springboot.controller;

import com.micro.springboot.model.Pedido;
import com.micro.springboot.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {


    private final PedidoService pedidoService;


    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public String criarPedido(@RequestBody Pedido pedido){
        pedidoService.salvarPedido(pedido);
        return "Pedido salvo e envvviado para processamento:" + pedido.getDescricao();
    }

    @GetMapping
    public List<Pedido> listarPedidos(){
        return pedidoService.listarPedidos();
    }
}
