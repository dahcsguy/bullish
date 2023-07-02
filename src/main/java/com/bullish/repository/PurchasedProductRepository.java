package com.bullish.repository;

import com.bullish.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedProductRepository extends CrudRepository<Product, Integer> {
}
