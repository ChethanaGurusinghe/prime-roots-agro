package edu.icet.service;

import edu.icet.model.dto.ExpenseDTO;
import edu.icet.model.dto.ItemDTO;
import edu.icet.model.dto.PurchaseOrderDTO;
import edu.icet.model.dto.SaleDTO;
import edu.icet.repository.ItemRepository;
import edu.icet.repository.PurchaseOrderRepository;
import edu.icet.repository.SaleRepository;
import edu.icet.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ItemRepository itemRepo;
    private final PurchaseOrderRepository poRepo;
    private final SaleRepository saleRepo;
    private final SupplierRepository supplierRepo;

    public ReportDTO getSummary() {
        return ReportDTO.builder()
                .lowStockCount(itemRepo.countByQtyLessThanEqualReorderLevel())
                .pendingPurchaseOrders(poRepo.countByStatus("PENDING"))
                .supplierCount(supplierRepo.count())
                .salesCount(saleRepo.count())
                .build();
    }
}
