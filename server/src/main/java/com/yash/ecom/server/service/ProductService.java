package com.yash.ecom.server.service;

import com.yash.ecom.server.pojo.ProductDetails;

import java.util.List;

public interface ProductService {

    List<ProductDetails> searchProductsWithDetails(String keywords);

}
