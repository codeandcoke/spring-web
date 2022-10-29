package com.svalero.amazonapi.service;

import com.svalero.amazonapi.domain.Order;
import com.svalero.amazonapi.domain.Product;
import com.svalero.amazonapi.domain.User;
import com.svalero.amazonapi.dto.OrderInDTO;
import com.svalero.amazonapi.dto.OrderOutDTO;

import java.util.List;

public interface OrderService {

    OrderOutDTO addOrder(OrderInDTO orderDTO, Product product, User user);
    List<Order> findOrders(User user);
}
