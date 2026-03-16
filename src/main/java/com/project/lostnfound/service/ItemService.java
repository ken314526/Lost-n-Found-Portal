package com.project.lostnfound.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.lostnfound.dto.ItemCreateRequest;
import com.project.lostnfound.dto.ItemResponse;
import com.project.lostnfound.exception.BadRequestException;
import com.project.lostnfound.exception.ResourceNotFoundException;
import com.project.lostnfound.model.Item;
import com.project.lostnfound.model.ItemStatus;
import com.project.lostnfound.model.User;
import com.project.lostnfound.repository.ItemRepository;
import com.project.lostnfound.repository.UserRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public ItemService(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public ItemResponse createItem(ItemCreateRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new BadRequestException("Item name is required");
        }
        if (request.getDescription() == null || request.getDescription().isBlank()) {
            throw new BadRequestException("Description is required");
        }
        if (request.getLocation() == null || request.getLocation().isBlank()) {
            throw new BadRequestException("Location is required");
        }
        if (request.getUploadedByUserId() == null) {
            throw new BadRequestException("uploadedByUserId is required");
        }

        User uploadedBy = userRepository.findById(request.getUploadedByUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + request.getUploadedByUserId()));

        Item item = new Item();
        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setLocation(request.getLocation());
        item.setFoundDate(LocalDateTime.now());
        item.setStatus(ItemStatus.FOUND);
        item.setUploadedBy(uploadedBy);

        Item saved = itemRepository.save(item);
        return toItemResponse(saved);
    }

    public List<ItemResponse> getAllItems() {
        return itemRepository.findAll().stream().map(this::toItemResponse).toList();
    }

    public ItemResponse getItemById(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + itemId));
        return toItemResponse(item);
    }

    public List<ItemResponse> getItemsByStatus(ItemStatus status) {
        return itemRepository.findByStatus(status).stream().map(this::toItemResponse).toList();
    }

    public ItemResponse updateItemStatus(Long itemId, ItemStatus status) {
        if (status == null) {
            throw new BadRequestException("Status is required");
        }

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + itemId));
        item.setStatus(status);

        Item updated = itemRepository.save(item);
        return toItemResponse(updated);
    }

    public Item getItemEntityById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + itemId));
    }

    private ItemResponse toItemResponse(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getLocation(),
                item.getFoundDate(),
                item.getStatus(),
                item.getUploadedBy().getId(),
                item.getUploadedBy().getName());
    }
}
