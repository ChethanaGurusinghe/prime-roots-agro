package edu.icet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "suppliers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SupplierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    @Column(nullable = false)
    private String name;

    private String email;

    private String phoneNo;

    @Column(columnDefinition = "TEXT")
    private String address;

    @CreationTimestamp
    private Instant createdAt;

    @OneToMany(mappedBy = "supplier")
    @ToString.Exclude
    @JsonIgnore
    private List<PurchaseOrderEntity> purchaseOrders;

}
