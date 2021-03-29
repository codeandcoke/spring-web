package com.sanvalero.myshop.service;

import com.sanvalero.myshop.domain.Order;
import com.sanvalero.myshop.domain.dto.OrderDTO;

import java.util.Set;

/**
 * Service de pedidos
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
public interface OrderService {

    Set<Order> findAll();
    Order addOrder(OrderDTO orderDTO);
}
