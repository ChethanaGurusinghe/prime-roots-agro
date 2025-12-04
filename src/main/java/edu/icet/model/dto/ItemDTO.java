package edu.icet.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {

    private Long itemId;
    private String name;
    private String sku;
    private BigDecimal unitPrice;
    private Integer qty;
    private LocalDate expiryDate;
    private Integer reorderLevel;

    private Long categoryId;
    private String categoryName;
}
