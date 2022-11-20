package com.codeandcoke.amazonapi.service;

import com.codeandcoke.amazonapi.domain.Product;
import com.codeandcoke.amazonapi.dto.ProductPatchDTO;
import com.codeandcoke.amazonapi.exception.ProductNotFoundException;
import com.codeandcoke.amazonapi.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProduct(long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<Product> findByCategory(String categoryName) {
        List<Product> products = productRepository.findByCategory(categoryName);
        return products;
    }

    @Override
    public List<Product> findByPrice(float price) {
        return productRepository.findByPrice(price);
    }

    @Override
    public List<Product> findByPriceAndCategory(float price, String category) {
        return productRepository.findByPriceAndCategory(price, category);
    }

    @Override
    public Product addProduct(Product product) {
        product.setCreationDate(LocalDate.now());
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }

    @Override
    public Product modifyProduct(long productId, Product product) throws ProductNotFoundException {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        modelMapper.map(product, existingProduct);
        existingProduct.setId(productId);

        return productRepository.save(existingProduct);
    }

    @Override
    public void patchProduct(long productId, ProductPatchDTO productPatchDTO) throws ProductNotFoundException {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        if (productPatchDTO.getField().equals("price")) {
            existingProduct.setPrice(Float.parseFloat(productPatchDTO.getValue()));
        }

        productRepository.save(existingProduct);
    }
}
