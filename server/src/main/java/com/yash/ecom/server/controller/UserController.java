package com.yash.ecom.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class UserController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
