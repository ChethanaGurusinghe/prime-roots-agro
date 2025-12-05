package edu.icet.repository;

import edu.icet.model.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity,Long> {
    boolean existsBySku(String sku);
}
