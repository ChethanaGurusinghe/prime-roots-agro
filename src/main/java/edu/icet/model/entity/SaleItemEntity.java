package edu.icet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "sale_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SaleItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleItemId;

    @Column(nullable = false)
    private Integer qty;

    @Column(nullable = false, precision = 13, scale = 2)
    private BigDecimal unitPrice;

    @Column(precision = 13, scale = 2)
    private BigDecimal subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    @JsonIgnore
    private SaleEntity sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;

}
