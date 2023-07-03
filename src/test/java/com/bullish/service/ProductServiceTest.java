package com.bullish.service;

import com.bullish.model.Product;
import com.bullish.repository.CartRepository;
import com.bullish.repository.ProductRepository;
import com.bullish.service.impl.CartServiceImpl;
import com.bullish.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class ProductServiceTest {


    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllProducts() {
        List<Product> productList = new ArrayList();
        productList.add(new Product());
        productList.add(new Product());
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> list = productService.getAllProducts();
        assertEquals(productList, list);

    }

    @Test
    void getProductById() {
        Optional<Product> product = Optional.of(new Product());
        when(productService.getProductById(anyInt())).thenReturn(product);
        assertEquals(productService.getProductById(1), product);
    }

    @Test
    void addProduct() {

    }

    @Test
    void deleteProduct() {
    }
}