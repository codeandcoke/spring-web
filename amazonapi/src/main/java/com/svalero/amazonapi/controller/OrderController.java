package com.svalero.amazonapi.controller;

import com.svalero.amazonapi.domain.Product;
import com.svalero.amazonapi.domain.User;
import com.svalero.amazonapi.dto.ErrorResponse;
import com.svalero.amazonapi.dto.OrderInDTO;
import com.svalero.amazonapi.dto.OrderOutDTO;
import com.svalero.amazonapi.exception.ProductNotFoundException;
import com.svalero.amazonapi.exception.UserNotFoundException;
import com.svalero.amazonapi.service.OrderService;
import com.svalero.amazonapi.service.ProductService;
import com.svalero.amazonapi.service.UserService;
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
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/user/{userId}/orders")
    public ResponseEntity<OrderOutDTO> addOrder(@PathVariable long userId, @RequestBody OrderInDTO orderDTO)
        throws ProductNotFoundException, UserNotFoundException {
        User user = userService.findUser(userId);
        Product product = productService.findProduct(orderDTO.getProductId());
        OrderOutDTO orderOutDTO = orderService.addOrder(orderDTO, product, user);
        return new ResponseEntity<>(orderOutDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{userId}/orders")
    public ResponseEntity<List<OrderInDTO>> getOrders(@PathVariable long userId) {
        return null;
    }

    // TODO Delete order

    // TODO Modify order

    // TODO Deliver order

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ProductNotFoundException pnfe) {
        ErrorResponse errorResponse = new ErrorResponse(101, pnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException unfe) {
        ErrorResponse errorResponse = new ErrorResponse(101, unfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
