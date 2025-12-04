package edu.icet.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ExpenseDTO {
    private Long expenseId;
    private String category;
    private String description;
    private BigDecimal amount;
    private String paymentMethod;
    private Instant date;

    private Long createdById;
    private String createdByName;
}
