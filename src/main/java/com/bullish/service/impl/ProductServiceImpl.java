package com.bullish.service.impl;

import com.bullish.model.CartItem;
import com.bullish.model.Product;
import com.bullish.repository.ProductRepository;
import com.bullish.service.CartService;
import com.bullish.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartService cartService;

    @Override
    public List<Product> getAllProducts() {
        Iterable<Product> iter = productRepository.findAll();
        List<Product> result = new ArrayList<>();
        iter.forEach(result::add);
        return result;
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(int productId) {
        List<CartItem> cartItems = cartService.getCartsWithProduct(productId);
        for (CartItem cartItem : cartItems) {
            cartService.deleteCartItem(cartItem.getCartItemId());
        }
        productRepository.deleteById(productId);
    }

}
