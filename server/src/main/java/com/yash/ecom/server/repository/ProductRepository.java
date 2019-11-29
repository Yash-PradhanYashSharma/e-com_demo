package com.yash.ecom.server.repository;

import com.yash.ecom.server.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByDescriptionContainingIgnoreCase(String keyword);
}
