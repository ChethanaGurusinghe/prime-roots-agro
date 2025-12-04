package edu.icet.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateExpenseRequest {
    private String category;
    private String description;
    private BigDecimal amount;
    private String paymentMethod;
    private Long createdById;
}
