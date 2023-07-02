package com.bullish.service;

import com.bullish.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(int id);

    void addProduct(Product product);

    void deleteProduct(int productId);

}
