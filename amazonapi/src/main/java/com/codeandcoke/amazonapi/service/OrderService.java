package com.codeandcoke.amazonapi.service;

import com.codeandcoke.amazonapi.domain.Order;
import com.codeandcoke.amazonapi.domain.Product;
import com.codeandcoke.amazonapi.domain.User;
import com.codeandcoke.amazonapi.dto.OrderInDTO;
import com.codeandcoke.amazonapi.dto.OrderOutDTO;

import java.util.List;

public interface OrderService {

    OrderOutDTO addOrder(OrderInDTO orderDTO, Product product, User user);
    List<Order> findOrders(User user);
}
