package com.codeandcoke.amazonapi.controller;

import com.codeandcoke.amazonapi.domain.Product;
import com.codeandcoke.amazonapi.domain.User;
import com.codeandcoke.amazonapi.dto.ErrorResponse;
import com.codeandcoke.amazonapi.dto.OrderInDTO;
import com.codeandcoke.amazonapi.dto.OrderOutDTO;
import com.codeandcoke.amazonapi.exception.ProductNotFoundException;
import com.codeandcoke.amazonapi.service.OrderService;
import com.codeandcoke.amazonapi.service.ProductService;
import com.codeandcoke.amazonapi.service.UserService;
import com.codeandcoke.amazonapi.exception.UserNotFoundException;
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
