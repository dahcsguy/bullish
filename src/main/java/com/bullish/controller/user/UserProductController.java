package com.bullish.controller.user;

import com.bullish.exception.ProductNotFoundException;
import com.bullish.model.Product;
import com.bullish.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class UserProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<Product> getProduct(int productId) {
        Optional<Product> optionalProduct = productService.getProductById(productId);
        return optionalProduct.map(ResponseEntity::ok).orElseThrow(ProductNotFoundException::new);
    }
}
