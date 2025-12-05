package edu.icet.service;

import edu.icet.model.dto.PurchaseOrderDTO;
import edu.icet.model.entity.ItemEntity;
import edu.icet.model.entity.PurchaseOrderEntity;
import edu.icet.model.entity.PurchaseOrderItemEntity;
import edu.icet.model.entity.SupplierEntity;
import edu.icet.model.request.CreatePORequest;
import edu.icet.model.request.ReceivePORequest;
import edu.icet.repository.ItemRepository;
import edu.icet.repository.PurchaseOrderItemRepository;
import edu.icet.repository.PurchaseOrderRepository;
import edu.icet.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PurchaseOrderService {

    private final PurchaseOrderRepository poRepo;
    private final PurchaseOrderItemRepository poItemRepo;
    private final SupplierRepository supplierRepo;
    private final ItemRepository itemRepo;

    public PurchaseOrderDTO createPO(CreatePurchaseOrderRequest req) {

        SupplierEntity supplier = supplierRepo.findById(req.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        PurchaseOrderEntity po = PurchaseOrderEntity.builder()
                .supplier(supplier)
                .expectedDate(req.getExpectedDate())
                .status("PENDING")
                .totalAmount(0.0)
                .build();

        PurchaseOrderEntity saved = poRepo.save(po);
        double total = 0;

        for (var i : req.getItems()) {
            ItemEntity item = itemRepo.findById(i.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            double lineTotal = i.getQty() * i.getUnitPrice();
            total += lineTotal;

            poItemRepo.save(PurchaseOrderItemEntity.builder()
                    .purchaseOrder(saved)
                    .item(item)
                    .qty(i.getQty())
                    .unitPrice(i.getUnitPrice())
                    .receivedQty(0)
                    .build());
        }

        saved.setTotalAmount(total);
        poRepo.save(saved);

        return toDTO(saved);
    }

    private PurchaseOrderDTO toDTO(PurchaseOrderEntity p) {
        return PurchaseOrderDTO.builder()
                .poId(p.getPoId())
                .supplierId(p.getSupplier().getSupplierId())
                .supplierName(p.getSupplier().getName())
                .totalAmount(p.getTotalAmount())
                .status(p.getStatus())
                .expectedDate(p.getExpectedDate())
                .build();
    }

}
