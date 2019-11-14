package com.yash.ecom.server.repository;

import com.yash.ecom.server.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByDescriptionContainingIgnoreCase(String keyword);
}
