package edu.icet.repository;

import edu.icet.model.entity.SaleItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItemEntity,Long> {
}
