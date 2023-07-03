package com.bullish.controller.user;

import com.bullish.exception.CartNotFoundException;
import com.bullish.model.Cart;
import com.bullish.model.CartItem;
import com.bullish.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("carts")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping
    public ResponseEntity<?> createCart() {
        cartService.createCart();
        return ResponseEntity.ok("Created new Cart");
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable int cartId) {
        Optional<Cart> optionalCart = cartService.getCartById(cartId);
        return optionalCart.map(ResponseEntity::ok).orElseThrow(CartNotFoundException::new);
    }

    @PostMapping(value = "/{cartId}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addItem(@PathVariable int cartId, @RequestBody CartItem cartItem) {
        cartService.addToCart(cartId, cartItem);
        return ResponseEntity.ok("CartItem added to Cart");
    }

    @DeleteMapping(value = "/{cartId}/delete/{cartItemId}")
    public ResponseEntity<?> deleteItem(@PathVariable int cartId, @PathVariable int cartItemId) {
        cartService.deleteCartItem(cartId, cartItemId);
        return ResponseEntity.ok("CartItem deleted from cart");
    }
}
