package edu.icet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;

    @Column(unique = true)
    private String invoiceNo;

    @CreationTimestamp
    private Instant date;

    @Column(precision = 13, scale = 2)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user; // who created the sale / cashier

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnore
    private List<SaleItemEntity> items;

}
