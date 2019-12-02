package com.yash.ecom.server.service;

import com.yash.ecom.server.entity.Cart;
import com.yash.ecom.server.pojo.CartDetails;

public interface CartService {
    Cart updateCart(CartDetails cart);
}
