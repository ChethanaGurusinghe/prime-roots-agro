package edu.icet.service;

import edu.icet.model.dto.ItemDTO;
import edu.icet.model.entity.CategoryEntity;
import edu.icet.model.entity.ItemEntity;
import edu.icet.model.request.CreateItemRequest;
import edu.icet.model.request.UpdateStockRequest;
import edu.icet.repository.CategoryRepository;
import edu.icet.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepo;
    private final CategoryRepository catRepo;

    public ItemDTO createItem(CreateItemRequest req) {
        CategoryEntity category = catRepo.findById(req.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        ItemEntity item = ItemEntity.builder()
                .name(req.getName())
                .unitPrice(req.getUnitPrice())
                .sku(req.getSku())
                .qty(req.getQty())
                .expiryDate(req.getExpiryDate())
                .reorderLevel(req.getReorderLevel())
                .category(category)
                .build();

        return toDTO(itemRepo.save(item));
    }

    private ItemDTO toDTO(ItemEntity i) {
        return ItemDTO.builder()
                .itemId(i.getItemId())
                .name(i.getName())
                .sku(i.getSku())
                .unitPrice(i.getUnitPrice())
                .qty(i.getQty())
                .expiryDate(i.getExpiryDate())
                .reorderLevel(i.getReorderLevel())
                .categoryId(i.getCategory().getCategoryId())
                .categoryName(i.getCategory().getCategoryName())
                .build();
    }
}
