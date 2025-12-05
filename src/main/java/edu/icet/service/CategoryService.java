package edu.icet.service;

import edu.icet.model.dto.CategoryDTO;
import edu.icet.model.entity.CategoryEntity;
import edu.icet.model.request.CreateCategoryRequest;
import edu.icet.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {


    private final CategoryRepository repo;

    public CategoryDTO createCategory(CreateCategoryRequest req) {
        CategoryEntity c = CategoryEntity.builder()
                .categoryName(req.getCategoryName())
                .categoryDescription(req.getCategoryDescription())
                .build();
        return toDTO(repo.save(c));
    }

    public List<CategoryDTO> getAll() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private CategoryDTO toDTO(CategoryEntity c) {
        return CategoryDTO.builder()
                .categoryId(c.getCategoryId())
                .categoryName(c.getCategoryName())
                .categoryDescription(c.getCategoryDescription())
                .build();
    }
}
