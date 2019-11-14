package com.yash.ecom.server.service;

import com.yash.ecom.server.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> searchProducts(String keywords);
}
