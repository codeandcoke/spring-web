package com.sanvalero.myshop.service;

import com.sanvalero.myshop.domain.Order;
import com.sanvalero.myshop.domain.OrderDetail;
import com.sanvalero.myshop.domain.Product;
import com.sanvalero.myshop.domain.dto.OrderDTO;
import com.sanvalero.myshop.repository.OrderDetailRepository;
import com.sanvalero.myshop.repository.OrderRepository;
import com.sanvalero.myshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

/**
 * Implementación del Service de pedidos
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public Set<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order addOrder(OrderDTO orderDTO) {
        String number = UUID.randomUUID().toString();
        Order newOrder = new Order();
        newOrder.setNumber(number);
        newOrder.setDate(LocalDate.now());
        newOrder.setPrice(orderDTO.getPrice());
        newOrder = orderRepository.save(newOrder);

        for (String productName : orderDTO.getProducts()) {
            Product product = productRepository.findByName(productName);
            OrderDetail detail = new OrderDetail();
            detail.setPrice(product.getPrice());
            detail.setQuantity(1);
            detail.setOrder(newOrder);
            detail.setProduct(product);
            newOrder.addDetail(detail);
            orderDetailRepository.save(detail);
        }

        return newOrder;
    }
}
