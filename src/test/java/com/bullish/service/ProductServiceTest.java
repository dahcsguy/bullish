package com.bullish.service;

import com.bullish.model.CartItem;
import com.bullish.model.Product;
import com.bullish.repository.ProductRepository;
import com.bullish.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @Mock
    CartService cartService;
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
        when(productRepository.findById(anyInt())).thenReturn(product);
        assertEquals(productService.getProductById(1), product);
    }

    @Test
    void addProduct() {
        Product product = new Product();
        productService.addProduct(product);
        verify(productRepository, times(1)).save(any());
    }

    @Test
    void deleteProduct() {
        when(cartService.getCartsWithProduct(anyInt())).thenReturn(Arrays.asList(new CartItem(), new CartItem()));
        productService.deleteProduct(1);
        verify(productRepository, times(1)).deleteById(any());
        verify(cartService, times(2)).deleteCartItem(anyInt());
    }
}