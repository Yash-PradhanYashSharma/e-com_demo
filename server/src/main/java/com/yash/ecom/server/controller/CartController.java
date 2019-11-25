package com.yash.ecom.server.controller;

import com.yash.ecom.server.entity.Cart;
import com.yash.ecom.server.entity.TotalAdjustments;
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
    public Cart initializeCart(@RequestBody Cart cart) {
        Cart cartObj = new Cart();
        cartObj.setCartDate(cart.getCartDate());
        cartObj.setUserId(cart.getUserId());
        cartObj.setCartItems(cart.getCartItems());
        cartObj.setCartAdjustments(cart.getCartAdjustments());
        return cartObj;
    }

    @PostMapping(path = "/update")
    @ResponseBody
    public Cart updateCart(@RequestBody Cart cart) {
        return cartService.updateCart(cart);
    }
}
