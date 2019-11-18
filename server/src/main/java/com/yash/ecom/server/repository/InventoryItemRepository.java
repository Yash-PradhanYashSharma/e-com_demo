package com.yash.ecom.server.repository;

import com.yash.ecom.server.entity.InventoryItem;
import org.springframework.data.repository.CrudRepository;

public interface InventoryItemRepository extends CrudRepository<InventoryItem, Long> {
}
