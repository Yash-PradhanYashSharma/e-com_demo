package com.yash.ecom.server.service.impl;

import com.yash.ecom.server.entity.InventoryItem;
import com.yash.ecom.server.entity.Product;
import com.yash.ecom.server.entity.ProductPrice;
import com.yash.ecom.server.entity.ProductPromo;
import com.yash.ecom.server.pojo.ProductDetails;
import com.yash.ecom.server.repository.InventoryItemRepository;
import com.yash.ecom.server.repository.ProductPriceRepository;
import com.yash.ecom.server.repository.ProductPromoRepository;
import com.yash.ecom.server.repository.ProductRepository;
import com.yash.ecom.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductPriceRepository productPriceRepository;

    @Autowired
    ProductPromoRepository productPromoRepository;

    @Autowired
    InventoryItemRepository inventoryItemRepository;

    @Override
    public List<ProductDetails> searchProductsWithDetails(String keyword) {

        List<ProductDetails> productDetails = new ArrayList<>();
        List<Product> products = productRepository.findByDescriptionContainingIgnoreCase(keyword);

        products.forEach(product -> {
            ProductDetails details = new ProductDetails();
            details.setProductId(product.getProductId());
            details.setProductName(product.getProductName());
            details.setProductDescription(product.getDescription());
            InventoryItem inventoryItem = product.getInventoryItemByInventoryItemId();
            details.setQuantity(inventoryItem.getQuantity());
            details.setSelectedQuantity(inventoryItem.getQuantity());


            List<ProductPrice> productPriceList = new ArrayList<>();
            List<ProductPrice> productPrices = productPriceRepository.findByProductId(product.getProductId());
            productPrices.forEach(productPrice -> {
                ProductPrice price = new ProductPrice();
                price.setPrice(productPrice.getPrice());
                price.setProductId(productPrice.getProductId());
                price.setDescription(productPrice.getDescription());
                price.setProductPriceTypeId(productPrice.getProductPriceTypeId());
                if (productPrice.getProductPriceTypeId().equals("DEFAULT_PRICE")) {
                    details.setPrice(productPrice.getPrice());
                }
                productPriceList.add(price);
            });
            details.setProductPrices(productPriceList);

            List<ProductPromo> productPromoList = new ArrayList<>();
            Iterable<ProductPromo> productPromos = productPromoRepository.findAll();
            productPromos.forEach(productPromo -> {
                ProductPromo promo = new ProductPromo();
                promo.setProductPromoId(productPromo.getProductPromoId());
                promo.setPromoName(productPromo.getPromoName());
                promo.setProductPromoCode(productPromo.getProductPromoCode());
                promo.setFromDate(productPromo.getFromDate());
                promo.setThruDate(productPromo.getThruDate());
                promo.setUseLimitPerCustomer(productPromo.getUseLimitPerCustomer());
                promo.setUseLimitPerCode(productPromo.getUseLimitPerCode());
                productPromoList.add(promo);
            });
            productDetails.add(details);
            details.setProductPromos(productPromoList);
        });

        return productDetails;
    }

}
