package edu.icet.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PurchaseOrderDTO {
    private Long poId;
    private BigDecimal totalAmount;
    private Instant createdAt;
    private LocalDate expectedDate;
    private String status;

    private Long supplierId;
    private String supplierName;

    private List<PurchaseOrderItemDTO> items;
}
