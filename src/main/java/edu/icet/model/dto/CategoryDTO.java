package edu.icet.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CategoryDTO {

    private Long categoryId;
    private String categoryName;
    private String categoryDescription;

}
