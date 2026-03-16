package com.project.lostnfound.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.lostnfound.model.Item;
import com.project.lostnfound.model.ItemStatus;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByStatus(ItemStatus status);
}
