package com.yash.ecom.server.controller;

import com.yash.ecom.server.pojo.ProductDetails;
import com.yash.ecom.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<ProductDetails> searchProducts(@RequestParam(name = "keyword") String keyword) {
        return productService.searchProductsWithDetails(keyword);
    }
}
