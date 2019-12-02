package com.yash.ecom.server.service.impl;

import com.yash.ecom.server.entity.Cart;
import com.yash.ecom.server.entity.CartItem;
import com.yash.ecom.server.pojo.CartDetails;
import com.yash.ecom.server.repository.CartItemRepository;
import com.yash.ecom.server.repository.CartRepository;
import com.yash.ecom.server.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public Cart updateCart(CartDetails cartDetails) {

        Cart cart = new Cart();
        cart.setUserId(cartDetails.getUserId());
        cart.setCartDate(cartDetails.getCartDate());
        cartRepository.save(cart);
        cartDetails.getItems().forEach(item -> {
            CartItem cartItem = new CartItem();
            cartItem.setProductId(item.getProductId());
            cartItem.setQuantity(item.getQuantity());
            cartItem.setUnitPrice(item.getPrice());
            cartItemRepository.save(cartItem);
        });
        return cart;
    }
}
