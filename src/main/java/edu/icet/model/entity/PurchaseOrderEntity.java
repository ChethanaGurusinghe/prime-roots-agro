package edu.icet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "purchase_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PurchaseOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long poId;   // PoID

    @Column(precision = 13, scale = 2)
    private BigDecimal totalAmount;

    @CreationTimestamp
    private Instant createdAt;

    private LocalDate expectedDate;

    @Enumerated(EnumType.STRING)
    private PurchaseOrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnore
    private List<PurchaseOrderItemEntity> items;

}
