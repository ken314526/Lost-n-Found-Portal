package com.project.lostnfound.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.lostnfound.dto.ItemCreateRequest;
import com.project.lostnfound.dto.ItemResponse;
import com.project.lostnfound.dto.UpdateItemStatusRequest;
import com.project.lostnfound.model.ItemStatus;
import com.project.lostnfound.service.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemResponse> createItem(@RequestBody ItemCreateRequest request) {
        ItemResponse response = itemService.createItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemResponse> getItemById(@PathVariable Long itemId) {
        return ResponseEntity.ok(itemService.getItemById(itemId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ItemResponse>> getItemsByStatus(@PathVariable ItemStatus status) {
        return ResponseEntity.ok(itemService.getItemsByStatus(status));
    }

    @PutMapping("/{itemId}/status")
    public ResponseEntity<ItemResponse> updateStatus(@PathVariable Long itemId,
            @RequestBody UpdateItemStatusRequest request) {
        return ResponseEntity.ok(itemService.updateItemStatus(itemId, request.getStatus()));
    }
}
