package com.yash.ecom.server.service.impl;

import com.yash.ecom.server.entity.Product;
import com.yash.ecom.server.repository.ProductRepository;
import com.yash.ecom.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByDescriptionContainingIgnoreCase(keyword);
    }
}
