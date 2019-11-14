package com.yash.ecom.server.controller;

import com.yash.ecom.server.entity.Product;
import com.yash.ecom.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> searchProducts(@RequestParam(name="keyword", required=false, defaultValue="shirt") String keyword) {
        return productService.searchProducts(keyword);
    }
}
