package com.svalero.helloapi.controller;

import com.svalero.helloapi.domain.ErrorResponse;
import com.svalero.helloapi.domain.Order;
import com.svalero.helloapi.dto.OrderInDto;
import com.svalero.helloapi.exception.ProductNotFoundException;
import com.svalero.helloapi.exception.UserNotFoundException;
import com.svalero.helloapi.service.OrderService;
import com.svalero.helloapi.service.ProductService;
import com.svalero.helloapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderService.findAll();
    }

    @GetMapping("/user/{userId}/orders")
    public List<Order> getUserOrders(@PathVariable long userId) throws UserNotFoundException {
        return orderService.findByUser(userId);
    }

    @PostMapping("/user/{userId}/orders")
    public void addOrder(@RequestBody OrderInDto orderInDto, @PathVariable long userId) throws UserNotFoundException, ProductNotFoundException {
        orderService.addOrder(orderInDto, userId);
    }

    // TODO Hacer el resto de operaciones hasta el CRUD

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException unfe) {
        ErrorResponse errorResponse = ErrorResponse.generalError(404, unfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> productNotFoundException(ProductNotFoundException pnfe) {
        ErrorResponse errorResponse = ErrorResponse.generalError(404, pnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
