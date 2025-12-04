package edu.icet.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrderItemDTO {
    private Long poItemId;
    private Long itemId;
    private String itemName;
    private Integer qty;
    private Integer receivedQty;
    private BigDecimal unitPrice;
}
