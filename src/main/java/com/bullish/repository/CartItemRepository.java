package com.bullish.repository;

import com.bullish.model.Cart;
import com.bullish.model.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Integer> {
}
