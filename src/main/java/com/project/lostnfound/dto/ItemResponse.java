package com.project.lostnfound.dto;

import java.time.LocalDateTime;

import com.project.lostnfound.model.ItemStatus;

public class ItemResponse {
    private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDateTime foundDate;
    private ItemStatus status;
    private Long uploadedByUserId;
    private String uploadedByName;

    public ItemResponse() {
    }

    public ItemResponse(Long id, String name, String description, String location, LocalDateTime foundDate,
            ItemStatus status, Long uploadedByUserId, String uploadedByName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.foundDate = foundDate;
        this.status = status;
        this.uploadedByUserId = uploadedByUserId;
        this.uploadedByName = uploadedByName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(LocalDateTime foundDate) {
        this.foundDate = foundDate;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public Long getUploadedByUserId() {
        return uploadedByUserId;
    }

    public void setUploadedByUserId(Long uploadedByUserId) {
        this.uploadedByUserId = uploadedByUserId;
    }

    public String getUploadedByName() {
        return uploadedByName;
    }

    public void setUploadedByName(String uploadedByName) {
        this.uploadedByName = uploadedByName;
    }
}
