package com.yash.ecom.server.service.impl;

import com.yash.ecom.server.entity.Cart;
import com.yash.ecom.server.repository.CartRepository;
import com.yash.ecom.server.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);
    }
}
