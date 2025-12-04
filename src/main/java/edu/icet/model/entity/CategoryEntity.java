package edu.icet.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false, unique = true)
    private String categoryName;

    @Column(columnDefinition = "TEXT")
    private String categoryDescription;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ItemEntity> items;
}
