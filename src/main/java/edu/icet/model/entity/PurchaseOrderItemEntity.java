package edu.icet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "purchase_order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PurchaseOrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long poItemId;

    @Column(nullable = false)
    private Integer qty;

    @Column(nullable = false, precision = 13, scale = 2)
    private BigDecimal unitPrice;

    // how many have been received so far for this PO item
    @Column(nullable = false)
    private Integer receivedQty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "po_id")
    @JsonIgnore
    private PurchaseOrderEntity purchaseOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;

}
