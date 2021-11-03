package com.shoppingcart.dio.repository;

import com.shoppingcart.dio.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Integer> {
}
