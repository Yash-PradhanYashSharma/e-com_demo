package com.yash.ecom.server.controller;

import com.yash.ecom.server.entity.Cart;
import com.yash.ecom.server.entity.Party;
import com.yash.ecom.server.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private PartyService partyService;

    @GetMapping(path = "/initialize", produces = "application/json", consumes = "application/json")
    public Cart initializeCart(@RequestBody Cart cart) {
        return cart;
    }
}
