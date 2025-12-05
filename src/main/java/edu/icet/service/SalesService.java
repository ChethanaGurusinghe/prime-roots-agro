package edu.icet.service;

import edu.icet.model.dto.SaleDTO;
import edu.icet.model.entity.ItemEntity;
import edu.icet.model.entity.SaleEntity;
import edu.icet.model.entity.SaleItemEntity;
import edu.icet.model.entity.UserEntity;
import edu.icet.model.request.CreateSaleRequest;
import edu.icet.repository.ItemRepository;
import edu.icet.repository.SaleItemRepository;
import edu.icet.repository.SaleRepository;
import edu.icet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SaleRepository saleRepo;
    private final SaleItemRepository saleItemRepo;
    private final ItemRepository itemRepo;
    private final UserRepository userRepo;

    public SaleDTO createSale(CreateSaleRequest req) {

        UserEntity user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        SaleEntity sale = SaleEntity.builder()
                .invoiceNo(req.getInvoiceNo())
                .paymentMethod(req.getPaymentMethod())
                .date(req.getDate())
                .totalAmount(0.0)
                .user(user)
                .build();

        SaleEntity saved = saleRepo.save(sale);
        double total = 0;

        for (var i : req.getItems()) {
            ItemEntity item = itemRepo.findById(i.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            if (item.getQty() < i.getQty())
                throw new RuntimeException("Not enough stock");

            item.setQty(item.getQty() - i.getQty());
            itemRepo.save(item);

            double line = i.getQty() * i.getUnitPrice();
            total += line;

            saleItemRepo.save(
                    SaleItemEntity.builder()
                            .sale(saved)
                            .item(item)
                            .qty(i.getQty())
                            .unitPrice(i.getUnitPrice())
                            .build()
            );
        }

        saved.setTotalAmount(total);
        saleRepo.save(saved);

        return toDTO(saved);
    }

    private SaleDTO toDTO(SaleEntity s) {
        return SaleDTO.builder()
                .saleId(s.getSaleId())
                .invoiceNo(s.getInvoiceNo())
                .paymentMethod(s.getPaymentMethod())
                .date(s.getDate())
                .totalAmount(s.getTotalAmount())
                .userId(s.getUser().getUserId())
                .userName(s.getUser().getFullName())
                .build();
    }
}
