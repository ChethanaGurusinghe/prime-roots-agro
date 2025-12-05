package edu.icet.repository;

import edu.icet.model.entity.PurchaseOrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItemEntity,Long> {
}
