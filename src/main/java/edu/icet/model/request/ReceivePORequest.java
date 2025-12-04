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
public class ReceivePORequest {
    private Long poId;

    private List<ReceiveItem> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReceiveItem {
        private Long poItemId;
        private Integer receivedQty;
    }
}
