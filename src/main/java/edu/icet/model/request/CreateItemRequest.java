package edu.icet.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateItemRequest {
    private String name;
    private String sku;
    private BigDecimal unitPrice;
    private Integer qty;
    private LocalDate expiryDate;
    private Integer reorderLevel;

    private Long categoryId;
}
