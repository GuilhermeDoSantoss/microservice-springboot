package com.micro.springboot.repository;

import com.micro.springboot.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
