package edu.icet.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePORequest {
    private Long supplierId;
    private LocalDate expectedDate;

    private List<POItemRequest> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class POItemRequest {
        private Long itemId;
        private Integer qty;
        private double unitPrice;
    }
}
