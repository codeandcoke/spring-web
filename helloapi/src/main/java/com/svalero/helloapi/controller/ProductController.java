package com.svalero.helloapi.controller;

import com.svalero.helloapi.domain.ErrorResponse;
import com.svalero.helloapi.domain.Product;
import com.svalero.helloapi.dto.ProductInDto;
import com.svalero.helloapi.dto.ProductOutDto;
import com.svalero.helloapi.exception.ProductNotFoundException;
import com.svalero.helloapi.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable long productId) throws ProductNotFoundException {
        logger.info("ini GET /product/" + productId);
        Optional<Product> optionalProduct = productService.findById(productId);
        Product product = optionalProduct.orElseThrow(() -> new ProductNotFoundException(productId));
        logger.info("end GET /product/" + productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAll(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "0") float price) {
        if (!(name.isEmpty()) && (price == 0)) {
            return new ResponseEntity<>(productService.findByName(name), HttpStatus.OK);
        } else if (!name.isEmpty() && (price != 0)) {
            return new ResponseEntity<>(productService.findByNameAndPrice(name, price), HttpStatus.OK);
        }

        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductOutDto> saveProduct(@Valid @RequestBody ProductInDto product) {
        ProductOutDto newProduct = productService.saveProduct(product);
        // TODO Deberíamos devolver la información del producto creado
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable long productId) throws ProductNotFoundException {
        productService.removeProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/product/{productId}")
    public void modifyProduct(@RequestBody Product product, @PathVariable long productId) {
        productService.modifyProduct(product, productId);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> productNotFoundException(ProductNotFoundException pnfe) {
        ErrorResponse errorResponse = ErrorResponse.generalError(404, pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return ResponseEntity.badRequest().body(ErrorResponse.validationError(errors));
    }
}
