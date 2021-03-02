package com.sanvalero.myshop.service;

import com.sanvalero.myshop.domain.Product;

import java.util.Set;

public interface ProductService {

    Set<Product> findAllProducts();
    Set<Product> findAllProducts(String category);
    void increasePrice(Product product);
}
