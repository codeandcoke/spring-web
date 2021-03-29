package com.sanvalero.myshop.controller;

import com.sanvalero.myshop.domain.Order;
import com.sanvalero.myshop.domain.dto.OrderDTO;
import com.sanvalero.myshop.service.OrderService;
import com.sanvalero.myshop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para pedidos
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
@RestController
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/orders", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Order> addOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.addOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
