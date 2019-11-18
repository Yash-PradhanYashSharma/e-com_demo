package com.yash.ecom.server.repository;

import com.yash.ecom.server.entity.ProductPromo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductPromoRepository extends CrudRepository<ProductPromo, Long> {
    List<ProductPromo> findByProductPromoId(String id);
}
