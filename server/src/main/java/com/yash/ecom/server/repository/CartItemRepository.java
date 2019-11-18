package com.yash.ecom.server.repository;

import com.yash.ecom.server.entity.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
}
