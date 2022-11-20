package com.codeandcoke.amazonapi.controller;

import com.codeandcoke.amazonapi.domain.Product;
import com.codeandcoke.amazonapi.dto.ErrorResponse;
import com.codeandcoke.amazonapi.dto.ProductPatchDTO;
import com.codeandcoke.amazonapi.exception.ProductNotFoundException;
import com.codeandcoke.amazonapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // Add product
    @PostMapping(value = "/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    // Remove product
    @DeleteMapping(value = "/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long productId) throws ProductNotFoundException {
        productService.deleteProductById(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Modify product
    @PutMapping(value = "/product/{productId}")
    public ResponseEntity<Product> modifyProduct(@PathVariable long productId, @RequestBody Product product) throws ProductNotFoundException {
        Product newProduct = productService.modifyProduct(productId, product);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    // Modify product (patch)
    @PatchMapping(value = "/product/{productId}")
    public ResponseEntity<Void> patchProduct(@PathVariable long productId, @RequestBody ProductPatchDTO productPatchDTO) throws ProductNotFoundException {
        productService.patchProduct(productId, productPatchDTO);
        return ResponseEntity.noContent().build();
    }

    // See all products
    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getProducts(@RequestParam(defaultValue = "0") float price,
                                     @RequestParam(defaultValue = "") String category) {
        List<Product> products = null;

        if ((price != 0) && (category.equals(""))) {
            products = productService.findByPrice(price);
        } else {
            products = productService.findAllProducts();
        }

        return ResponseEntity.ok(products);
    }

    // See a product
    @GetMapping(value = "/product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable long productId) throws ProductNotFoundException {
        Product product = productService.findProduct(productId);
        return ResponseEntity.ok(product);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ProductNotFoundException pnfe) {
        ErrorResponse errorResponse = new ErrorResponse(101, pnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(999, "Error interno del servidor");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}