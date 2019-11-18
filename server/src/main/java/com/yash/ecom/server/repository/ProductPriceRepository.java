package com.yash.ecom.server.repository;

import com.yash.ecom.server.entity.ProductPrice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductPriceRepository extends CrudRepository<ProductPrice, Long> {

    ProductPrice findByProductIdAndProductPriceTypeId(String productId, String productPriceTypeId);

    List<ProductPrice> findByProductId(String productId);

}
