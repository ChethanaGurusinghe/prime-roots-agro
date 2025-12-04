package edu.icet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;            // corresponds to itemID in ER

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false, precision = 13, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private Integer qty;           // current stock

    private LocalDate expiryDate;  // nullable for non-perishable items

    @Column(nullable = false)
    private Integer reorderLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private CategoryEntity category;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

}
