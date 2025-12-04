package edu.icet.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SaleItemDTO {
    private Long saleItemId;
    private Long itemId;
    private String itemName;
    private Integer qty;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
}
