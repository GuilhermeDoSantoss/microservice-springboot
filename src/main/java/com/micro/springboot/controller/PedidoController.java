package com.micro.springboot.controller;

import com.micro.springboot.model.Pedido;
import com.micro.springboot.service.PedidoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final RabbitTemplate rabbitTemplate;
    private final PedidoService pedidoService;


    public PedidoController(RabbitTemplate rabbitTemplate, PedidoService pedidoService) {
        this.rabbitTemplate = rabbitTemplate;
        this.pedidoService = pedidoService;
    }

    @Value("${broker.queue.processamento.name}")
    private String routingKey;

    @PostMapping
    public String criarPedido(@RequestBody Pedido pedido){
        Pedido pedidoSalvo = pedidoService.salvarPedido(pedido);
        rabbitTemplate.convertAndSend("", routingKey, pedidoSalvo.getDescricao());

        return "Pedido salvo e envvviado para processamento:" + pedido.getDescricao();
    }

    @GetMapping
    public List<Pedido> listarPedidos(){
        return pedidoService.listarPedidos();
    }
}
