package com.svalero.amazonapi.service;

import com.svalero.amazonapi.domain.Product;
import com.svalero.amazonapi.dto.ProductPatchDTO;
import com.svalero.amazonapi.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();
    Product findProduct(long id) throws ProductNotFoundException;
    List<Product> findByCategory(String categoryName);
    List<Product> findByPrice(float price);
    List<Product> findByPriceAndCategory(float price, String category);

    Product addProduct(Product product);
    void deleteProductById(long productId) throws ProductNotFoundException;
    Product modifyProduct(long productId, Product product) throws ProductNotFoundException;
    void patchProduct(long productId, ProductPatchDTO productPatchDTO) throws ProductNotFoundException;
}
