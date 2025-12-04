package edu.icet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    private String category; // could be enum, kept string for flexibility

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 13, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @CreationTimestamp
    private Instant date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @JsonIgnore
    private UserEntity createdBy;

}
