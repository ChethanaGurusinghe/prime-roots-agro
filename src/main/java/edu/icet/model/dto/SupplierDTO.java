package edu.icet.model.dto;

import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SupplierDTO {
    private Long supplierId;
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private Instant createdAt;
}
