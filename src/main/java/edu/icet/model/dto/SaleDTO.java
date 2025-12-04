package edu.icet.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SaleDTO {
    private Long saleId;
    private String invoiceNo;
    private Instant date;
    private BigDecimal totalAmount;
    private String paymentMethod;

    private Long userId;
    private String userName;

    private List<SaleItemDTO> items;
}
