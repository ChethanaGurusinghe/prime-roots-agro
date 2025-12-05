package edu.icet.repository;

import edu.icet.model.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<SaleEntity,Long> {
}
