package com.svalero.helloapi.service;

import com.svalero.helloapi.domain.Order;
import com.svalero.helloapi.domain.Product;
import com.svalero.helloapi.domain.User;
import com.svalero.helloapi.dto.OrderInDto;
import com.svalero.helloapi.exception.ProductNotFoundException;
import com.svalero.helloapi.exception.UserNotFoundException;
import com.svalero.helloapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByUser(long userId) throws UserNotFoundException {
        User user = userService.findById(userId);
        return orderRepository.findByUser(user);
    }

    public void addOrder(OrderInDto orderInDto, long userId) throws UserNotFoundException, ProductNotFoundException {
        Order order = new Order();

        User user = userService.findById(userId);
        order.setUser(user);

        List<Product> products = new ArrayList<>();
        for (long productId: orderInDto.getProductIds()) {
            Product product = productService.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
            products.add(product);
        }
        order.setProducts(products);

        order.setNumber(UUID.randomUUID().toString());
        order.setCreationDate(LocalDate.now());
        orderRepository.save(order);
    }

    // TODO hacer el resto de métodos hasta el CRUD
}
