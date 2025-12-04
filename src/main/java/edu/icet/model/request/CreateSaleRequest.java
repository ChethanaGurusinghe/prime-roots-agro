package edu.icet.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSaleRequest {
    private String paymentMethod;
    private Long userId;

    private List<SaleItemRequest> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaleItemRequest {
        private Long itemId;
        private Integer qty;
    }
}
