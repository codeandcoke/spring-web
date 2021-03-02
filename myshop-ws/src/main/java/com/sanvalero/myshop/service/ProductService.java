package com.sanvalero.myshop.service;

import com.sanvalero.myshop.domain.Product;

import java.util.Optional;
import java.util.Set;

/**
 * Service de productos
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
public interface ProductService {

    Set<Product> findAll();
    Set<Product> findByCategory(String category);
    Optional<Product> findById(long id);
    Product addProduct(Product product);
    Product modifyProduct(long id, Product newProduct);
    void deleteProduct(long id);
}
