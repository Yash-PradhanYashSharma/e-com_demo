package com.yash.ecom.server.controller;

import com.yash.ecom.server.entity.Cart;
import com.yash.ecom.server.entity.CartItem;
import com.yash.ecom.server.pojo.CartDetails;
import com.yash.ecom.server.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping(path = "/initialize")
    @ResponseBody
    public CartDetails initializeCart(@RequestBody CartDetails cart) {
        Cart cartObj = new Cart();
        cartObj.setCartDate(cart.getCartDate());
        cartObj.setUserId(cart.getUserId());
        return cart;
    }

    @PostMapping(path = "/update")
    @ResponseBody
    public Cart updateCart(@RequestBody CartDetails cart) {
        return cartService.updateCart(cart);
    }
}
