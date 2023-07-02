package com.bullish.repository;

import com.bullish.model.Order;
import com.bullish.model.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {
}
