package com.svalero.helloapi.service;

import com.svalero.helloapi.domain.ErrorResponse;
import com.svalero.helloapi.domain.Product;
import com.svalero.helloapi.dto.ProductInDto;
import com.svalero.helloapi.dto.ProductOutDto;
import com.svalero.helloapi.exception.ProductNotFoundException;
import com.svalero.helloapi.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findByNameAndPrice(String name, float price) {
        return productRepository.findByNameAndPrice(name, price);
    }

    public ProductOutDto saveProduct(ProductInDto productInDto) {
        Product product = new Product();
        modelMapper.map(productInDto, product);
        product.setRegistrationDate(LocalDate.now());
        Product newProduct = productRepository.save(product);

        ProductOutDto productOutDto = new ProductOutDto();
        modelMapper.map(newProduct, productOutDto);

        return productOutDto;
    }

    public void removeProduct(long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        productRepository.delete(product);
    }

    public void modifyProduct(Product newProduct, long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Product existingProduct = product.get();
            existingProduct.setName(newProduct.getName());
            existingProduct.setDescription(newProduct.getDescription());
            existingProduct.setPrice(newProduct.getPrice());
            productRepository.save(existingProduct);
        }
    }
}
