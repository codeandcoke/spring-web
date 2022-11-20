package com.codeandcoke.amazonapi.service;

import com.codeandcoke.amazonapi.domain.Order;
import com.codeandcoke.amazonapi.domain.Product;
import com.codeandcoke.amazonapi.domain.User;
import com.codeandcoke.amazonapi.dto.OrderInDTO;
import com.codeandcoke.amazonapi.dto.OrderOutDTO;
import com.codeandcoke.amazonapi.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderOutDTO addOrder(OrderInDTO orderDTO, Product product, User user) {
        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setProduct(product);
        order.setUser(user);
        modelMapper.map(orderDTO, order);
        Order newOrder = orderRepository.save(order);

        OrderOutDTO orderOutDTO = new OrderOutDTO();
        modelMapper.map(newOrder, orderOutDTO);
        return orderOutDTO;
    }

    @Override
    public List<Order> findOrders(User user) {
        return orderRepository.findByUser(user);
    }
}
