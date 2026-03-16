package com.project.lostnfound.dto;

public class ItemCreateRequest {
    private String name;
    private String description;
    private String location;
    private Long uploadedByUserId;

    public ItemCreateRequest() {
    }

    public ItemCreateRequest(String name, String description, String location, Long uploadedByUserId) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.uploadedByUserId = uploadedByUserId;
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

    public Long getUploadedByUserId() {
        return uploadedByUserId;
    }

    public void setUploadedByUserId(Long uploadedByUserId) {
        this.uploadedByUserId = uploadedByUserId;
    }
}
